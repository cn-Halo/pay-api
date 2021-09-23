package io.github.halo.pay.api.resp;

import com.github.wxpay.sdk.WXPayConstants;
import io.github.halo.pay.util.DateUtil;
import io.github.halo.pay.util.MathUtil;

import java.util.Map;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultWXRespConvertManager<T> implements WXRespConvertManager<T> {

    @Override
    public WXRespConvert wapPayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };

    }

    @Override
    public WXRespConvert facePayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
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
            public T convert(Map resp) {
                return null;
            }
        };
    }

    @Override
    public WXRespConvert closeRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };
    }

    @Override
    public WXRespConvert cancelRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };
    }

    @Override
    public WXRespConvert downloadBillRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };
    }
}
