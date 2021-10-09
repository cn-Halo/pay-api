package io.github.halo.pay.api.resp.builder;

import io.github.halo.pay.api.resp.OrderQueryResp;

/**
 * @author yzm
 * @date 2021/10/9 21:35
 */
public class OrderQueryRespBuilder {

    private String tradeNo;
    private String outTradeNo;
    private String tradeStatus;
    private String tradeStatusDesc;
    private String totalAmount;
    private String gmtPayment;


    public OrderQueryRespBuilder tradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }


    public OrderQueryRespBuilder outTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public OrderQueryRespBuilder tradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }


    public OrderQueryRespBuilder tradeStatusDesc(String tradeStatusDesc) {
        this.tradeStatusDesc = tradeStatusDesc;
        return this;
    }


    public OrderQueryRespBuilder totalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }


    public OrderQueryRespBuilder gmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
        return this;
    }

    public static OrderQueryRespBuilder instance() {
        return new OrderQueryRespBuilder();
    }

    public OrderQueryResp build() {
        return new OrderQueryResp() {

            @Override
            public String tradeNo() {
                return tradeNo;
            }

            @Override
            public String outTradeNo() {
                return outTradeNo;
            }

            @Override
            public String tradeStatus() {
                return tradeStatus;
            }

            @Override
            public String tradeStatusDesc() {
                return tradeStatusDesc;
            }

            @Override
            public String totalAmount() {
                return totalAmount;
            }

            @Override
            public String gmtPayment() {
                return gmtPayment;
            }
        };
    }


}
