package io.github.halo.pay.service;

import com.alipay.api.AlipayClient;
import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.PayApi;
import io.github.halo.pay.api.impl.AliPayApiImpl;
import io.github.halo.pay.api.impl.WXPayApiImpl;
import io.github.halo.pay.service.param.UnitePayParam;

/**
 * @author yzm
 * @date 2021/9/24 22:44
 */
public class PayService {
    private PayApi aliPayApi;
    private PayApi wxPayApi;

    public PayService(AlipayClient alipayClient, WXPay wxPayClient) {
        this.aliPayApi = new AliPayApiImpl(alipayClient);
        this.wxPayApi = new WXPayApiImpl(wxPayClient);
    }

    public <T> T pay(UnitePayParam payParam) throws Exception {
        if (payParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (payParam.payType().isALI() && payParam.payType().isWapPay()) {
            return (T) aliPayApi.wapPay(payParam);
        }
        if (payParam.payType().isALI() && payParam.payType().isFacePay()) {
            return (T) aliPayApi.facePay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isWapPay()) {
            return (T) wxPayApi.wapPay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isFacePay()) {
            return (T) wxPayApi.facePay(payParam);
        }
        throw new UnsupportedOperationException("unsupported payType");
    }

}
