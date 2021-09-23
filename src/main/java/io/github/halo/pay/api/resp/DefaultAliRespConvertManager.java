package io.github.halo.pay.api.resp;

import com.alipay.api.AlipayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
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

                } else {
                }
                System.out.println(resp);
                return null;
            }
        };
    }

    @Override
    public AliRespConvert facePayRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                System.out.println(resp);
                return null;
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
                return null;
            }
        };
    }

    @Override
    public AliRespConvert closeRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }

    @Override
    public AliRespConvert cancelRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }

    @Override
    public AliRespConvert downloadBillRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }
}
