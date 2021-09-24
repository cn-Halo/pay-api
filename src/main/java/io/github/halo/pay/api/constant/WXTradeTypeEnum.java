package io.github.halo.pay.api.constant;

/**
 * Created on 2021/9/24.
 *
 * @author yzm
 */
public enum WXTradeTypeEnum {

    /**
     * JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，不同trade_type决定了调起支付的方式，请根据支付产品正确上传
     */
    JSAPI,
    NATIVE,
    APP,
    MWEB;
}
