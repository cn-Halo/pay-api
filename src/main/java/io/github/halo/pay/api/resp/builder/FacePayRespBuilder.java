package io.github.halo.pay.api.resp.builder;

import io.github.halo.pay.api.resp.FacePayResp;

/**
 * Created on 2021/10/9.
 *
 * @author yzm
 */
public class FacePayRespBuilder {

    private String tradeNo;
    private String outTradeNo;
    private String totalAmount;
    private String gmtPayment;
    private String tradeStatus;
    private String tradeStatusDesc;


    public static FacePayRespBuilder instance() {
        return new FacePayRespBuilder();
    }

    public FacePayRespBuilder tradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
        return this;
    }

    public FacePayRespBuilder outTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
        return this;
    }

    public FacePayRespBuilder totalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
        return this;
    }

    public FacePayRespBuilder gmtPayment(String gmtPayment) {
        this.gmtPayment = gmtPayment;
        return this;
    }

    public FacePayRespBuilder tradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
        return this;
    }

    public FacePayRespBuilder tradeStatusDesc(String tradeStatusDesc) {
        this.tradeStatusDesc = tradeStatusDesc;
        return this;
    }

    public FacePayResp build() {
        return new FacePayResp() {
            @Override
            public String tradeNo() {
                return tradeNo;
            }

            @Override
            public String outTradeNo() {
                return outTradeNo;
            }

            @Override
            public String totalAmount() {
                return totalAmount;
            }

            @Override
            public String gmtPayment() {
                return gmtPayment;
            }

            @Override
            public String tradeStatus() {
                return tradeStatus;
            }

            @Override
            public String tradeStatusDesc() {
                return tradeStatusDesc;
            }
        };
    }


}
