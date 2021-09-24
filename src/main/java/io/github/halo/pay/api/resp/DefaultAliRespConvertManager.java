package io.github.halo.pay.api.resp;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.*;
import io.github.halo.pay.util.DateUtil;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultAliRespConvertManager<T> implements AliRespConvertManager<T> {

    @Override
    public AliRespConvert wapPayRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                if (resp.isSuccess()) {
                    AlipayTradeWapPayResponse response = (AlipayTradeWapPayResponse) resp;
                    WapPayResp wapPayResp = new WapPayResp() {
                        @Override
                        public String data() {
                            return response.getBody();
                        }
                    };
                    return (T) PayApiRespBuilder.success(wapPayResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }
            }
        };
    }

    @Override
    public AliRespConvert facePayRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                AlipayTradePayResponse response = (AlipayTradePayResponse) resp;
                String code = resp.getCode();
                FacePayResp facePayResp = new FacePayResp() {
                    @Override
                    public String tradeNo() {
                        return response.getTradeNo();
                    }

                    @Override
                    public String outTradeNo() {
                        return response.getOutTradeNo();
                    }

                    @Override
                    public String totalAmount() {
                        return response.getTotalAmount();
                    }

                    @Override
                    public String gmtPayment() {
                        return DateUtil.dateToString(response.getGmtPayment(), DateUtil.FULL_FORMAT);
                    }
                };
                //支付成功（10000）
                if ("10000".equals(code)) {
                    return (T) PayApiRespBuilder.success(facePayResp, resp);
//                } else if ("40004".equals(code)) {
//                    //支付失败（40004）
//                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
//                } else if ("10003".equals(code)) {
//                    //等待用户付款（10003）
                } else {
                    //未知异常（20000）

                    //除支付成功外 其余都视作业务失败
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }
            }
        };
    }

    @Override
    public AliRespConvert orderQueryRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                if (resp.isSuccess()) {
                    AlipayTradeQueryResponse response = (AlipayTradeQueryResponse) resp;
                    OrderQueryResp orderQueryResp = new OrderQueryResp() {
                        @Override
                        public String tradeNo() {
                            return response.getTradeNo();
                        }

                        @Override
                        public String outTradeNo() {
                            return response.getOutTradeNo();
                        }

                        @Override
                        public String tradeStatus() {
                            return response.getTradeStatus();
                        }

                        @Override
                        public String totalAmount() {
                            return response.getTotalAmount();
                        }

                        @Override
                        public String gmtPayment() {
                            //使用 '本次交易打款给卖家的时间' 作为付款时间
                            return DateUtil.dateToString(response.getSendPayDate(), DateUtil.FULL_FORMAT);
                        }
                    };
                    return (T) PayApiRespBuilder.success(orderQueryResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }
            }
        };
    }

    @Override
    public AliRespConvert refundRespConvert() {
        return (AliRespConvert<T>) resp -> {
            if (resp.isSuccess()) {
                AlipayTradeRefundResponse response = (AlipayTradeRefundResponse) resp;
                RefundResp refundResp = new RefundResp() {
                    @Override
                    public String refundNo() {
                        return response.getTradeNo();
                    }

                    @Override
                    public String outTradeNo() {
                        return response.getOutTradeNo();
                    }

                    @Override
                    public String refundFee() {
                        return response.getRefundFee();
                    }
                };
                return (T) PayApiRespBuilder.success(refundResp, resp);
            } else {
                return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
            }
        };
    }

    @Override
    public AliRespConvert refundQueryRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                if (resp.isSuccess()) {
                    AlipayTradeFastpayRefundQueryResponse response = (AlipayTradeFastpayRefundQueryResponse) resp;
                    RefundQueryResp refundQueryRespResp = new RefundQueryResp() {

                        @Override
                        public String outTradeNo() {
                            return response.getOutTradeNo();
                        }

                        @Override
                        public String tradeNo() {
                            return response.getTradeNo();
                        }

                        @Override
                        public String totalAmount() {
                            return response.getTotalAmount();
                        }

                        @Override
                        public String outRefundNo() {
                            return response.getOutRequestNo();
                        }

                        @Override
                        public String refundFee() {
                            return refundFee();
                        }

                        @Override
                        public String refundStatus() {
                            return response.getRefundStatus();
                        }

                        @Override
                        public String gmtRefundPay() {
                            return response.getGmtRefundPay() == null ? null : DateUtil.dateToString(response.getGmtRefundPay(), DateUtil.FULL_FORMAT);
                        }
                    };
                    return (T) PayApiRespBuilder.success(refundQueryRespResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }

            }
        };
    }

    @Override
    public AliRespConvert closeRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                if (resp.isSuccess()) {
                    AlipayTradeFastpayRefundQueryResponse response = (AlipayTradeFastpayRefundQueryResponse) resp;
                    CloseResp closeResp = new CloseResp() {
                    };
                    return (T) PayApiRespBuilder.success(closeResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }
            }
        };
    }

    @Override
    public AliRespConvert cancelRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                AlipayTradeCancelResponse response = (AlipayTradeCancelResponse) resp;
                CancelResp cancelResp = new CancelResp() {
                    @Override
                    public String recall() {
                        return response.getRetryFlag();
                    }
                };
                if (resp.isSuccess()) {
                    return (T) PayApiRespBuilder.success(cancelResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp).data(cancelResp);
                }
            }
        };
    }

    @Override
    public AliRespConvert downloadBillRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                if (resp.isSuccess()) {
                    AlipayDataDataserviceBillDownloadurlQueryResponse response = (AlipayDataDataserviceBillDownloadurlQueryResponse) resp;
                    DownloadBillResp downloadBillResp = new DownloadBillResp() {
                        @Override
                        public String data() {
                            return response.getBillDownloadUrl();
                        }
                    };
                    return (T) PayApiRespBuilder.success(downloadBillResp, resp);
                } else {
                    return (T) PayApiRespBuilder.subFail(resp.getMsg() + ":" + resp.getSubMsg(), resp);
                }
            }
        };
    }
}
