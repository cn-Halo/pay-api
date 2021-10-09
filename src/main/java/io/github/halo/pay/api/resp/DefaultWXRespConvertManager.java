package io.github.halo.pay.api.resp;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import com.google.gson.Gson;
import io.github.halo.pay.api.resp.builder.FacePayRespBuilder;
import io.github.halo.pay.service.constant.TradeStatusEnum;
import io.github.halo.pay.util.DateUtil;
import io.github.halo.pay.util.MathUtil;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultWXRespConvertManager<T> implements WXRespConvertManager<T> {
    private WXPay wxPayClient;

    public DefaultWXRespConvertManager(WXPay wxPayClient) {
        this.wxPayClient = wxPayClient;
    }

    @Override
    public WXRespConvert wapPayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    WapPayResp wapPayResp = new WapPayResp() {
                        @Override
                        public String data() throws Exception {
                            String tradeType = resp.get("trade_type");
                            //JSAPI支付（或小程序支付） 入参openid必传
                            if ("JSAPI".equals(tradeType) || "APP".equals(tradeType)) {
                                //由于微信sdk没有提供WXPay的config字段的get方法 所以使用反射获取
                                Field configField = WXPay.class.getDeclaredField("config");
                                configField.setAccessible(true);
                                WXPayConfig wxPayConfig = (WXPayConfig) configField.get(wxPayClient);
                                TreeMap map = new TreeMap();
                                map.put("appId", resp.get("appid"));
                                map.put("timeStamp", String.valueOf(new Date().getTime() / 1000));
                                map.put("nonceStr", WXPayUtil.generateNonceStr());
                                map.put("package", "prepay_id=" + resp.get("prepay_id"));
                                map.put("signType", WXPayConstants.SignType.HMACSHA256.name());
                                map.put("paySign", WXPayUtil
                                        .generateSignature(map, wxPayConfig.getKey(), WXPayConstants.SignType.HMACSHA256));
                                return new Gson().toJson(map);
                            } else if ("NATIVE".equals(tradeType)) {
                                //NATIVE支付
                                return resp.get("code_url");
                            } else if ("MWEB".equals(tradeType)) {
                                //H5支付 不用传值openid
                                return resp.get("mweb_url");
                            }
                            return null;
                        }
                    };
                    return (T) PayApiRespBuilder.success(wapPayResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }
            }
        };

    }

    @Override
    public WXRespConvert facePayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code"))) {
                    //构建响应体
                    FacePayRespBuilder facePayRespBuilder = FacePayRespBuilder.instance()
                            .tradeNo(resp.get("transaction_id"))
                            .outTradeNo(resp.get("out_trade_no"))
                            .totalAmount(resp.get("total_fee") == null ? null : MathUtil.fenToYuan(resp.get("total_fee")))
                            .gmtPayment(resp.get("time_end") == null ? null : DateUtil.string2String(resp.get("time_end"), DateUtil.YYYYMMDDHHMMSS_FORMAT, DateUtil.FULL_FORMAT));

                    FacePayResp facePayResp = null;
                    //支付成功
                    if (WXPayConstants.SUCCESS.equals(resp.get("result_code"))
                            && "MICROPAY".equals(resp.get("trade_type"))) {
                        facePayResp = facePayRespBuilder.tradeStatus(TradeStatusEnum.TRADE_SUCCESS.name()).build();
                    } else {
                        //业务失败(result_code为FAIL)分为：支付结果未知 、支付确认失败
                        //支付结果未知，需要轮询查询接口
                        if ("SYSTEMERROR".equals(resp.get("err_code")) || "BANKERROR".equals(resp.get("err_code"))
                                || "USERPAYING".equals(resp.get("err_code"))) {
                            facePayResp = facePayRespBuilder.tradeStatus(TradeStatusEnum.WAIT_BUYER_PAY.name()).build();
                        } else {
                            //支付确认失败，需要更换订单号重新下单支付
                            facePayResp = facePayRespBuilder.tradeStatus(TradeStatusEnum.TRADE_FAILED.name()).build();
                        }
                    }

                    return (T) PayApiRespBuilder.success(facePayResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg"), resp);
                }
            }
        };
    }

    @Override
    public WXRespConvert orderQueryRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {

                    OrderQueryResp orderQueryResp = new OrderQueryResp() {
                        @Override
                        public String tradeNo() {
                            return resp.get("transaction_id");
                        }

                        @Override
                        public String outTradeNo() {
                            return resp.get("out_trade_no");
                        }

                        @Override
                        public String tradeStatus() {
                            return resp.get("trade_state");
                        }

                        @Override
                        public String totalAmount() {
                            return resp.get("total_fee") == null ? null : MathUtil.fenToYuan(resp.get("total_fee"));
                        }

                        @Override
                        public String gmtPayment() {
                            //订单支付时间，格式为yyyyMMddHHmmss
                            return resp.get("time_end") == null ? null : DateUtil.string2String(resp.get("time_end"), DateUtil.YYYYMMDDHHMMSS_FORMAT, DateUtil.FULL_FORMAT);
                        }
                    };
                    return (T) PayApiRespBuilder.success(orderQueryResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }
            }
        };
    }

    @Override
    public WXRespConvert refundRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                //注意 此处result_code为SUCCESS，仅表示退款申请接收成功，结果通过退款查询接口查询
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    RefundResp refundResp = new RefundResp() {
                        @Override
                        public String refundNo() {
                            return resp.get("refund_id");
                        }

                        @Override
                        public String outTradeNo() {
                            return resp.get("out_trade_no");
                        }

                        @Override
                        public String refundFee() {
                            return resp.get("refund_fee") == null ? null : MathUtil.fenToYuan(resp.get("refund_fee"));
                        }
                    };
                    return (T) PayApiRespBuilder.success(refundResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }

            }
        };
    }

    @Override
    public WXRespConvert refundQueryRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    RefundQueryResp refundQueryResp = new RefundQueryResp() {
                        @Override
                        public String outTradeNo() {
                            return resp.get("out_trade_no");
                        }

                        @Override
                        public String tradeNo() {
                            return resp.get("transaction_id");
                        }

                        @Override
                        public String totalAmount() {
                            return resp.get("total_fee");
                        }

                        @Override
                        public String outRefundNo() {
                            return resp.get("out_refund_no_0");
                        }

                        @Override
                        public String refundFee() {
                            return resp.get("refund_fee_0") == null ? null : MathUtil.fenToYuan(resp.get("refund_fee_0"));
                        }

                        @Override
                        public String refundStatus() {
                            return resp.get("refund_status_0");
                        }

                        @Override
                        public String gmtRefundPay() {
                            return resp.get("refund_success_time_0");
                        }
                    };
                    return (T) PayApiRespBuilder.success(refundQueryResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }
            }
        };
    }

    @Override
    public WXRespConvert closeRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    CloseResp closeResp = new CloseResp() {
                    };
                    return (T) PayApiRespBuilder.success(closeResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }
            }
        };
    }

    @Override
    public WXRespConvert cancelRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                CancelResp cancelResp = new CancelResp() {
                    @Override
                    public String recall() {
                        return resp.get("recall");
                    }
                };
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    return (T) PayApiRespBuilder.success(cancelResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp).data(cancelResp);
                }
            }
        };
    }

    @Override
    public WXRespConvert downloadBillRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map<String, String> resp) {
                if (WXPayConstants.SUCCESS.equals(resp.get("return_code")) &&
                        WXPayConstants.SUCCESS.equals(resp.get("result_code"))) {
                    DownloadBillResp downloadBillResp = new DownloadBillResp() {
                        @Override
                        public String data() {
                            return resp.get("data");
                        }
                    };
                    return (T) PayApiRespBuilder.success(downloadBillResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.get("return_msg") + ":" + resp.get("err_code_des"), resp);
                }
            }
        };
    }
}
