package io.github.halo.pay.api.param;

import io.github.halo.pay.api.constant.WXTradeTypeEnum;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface WapPayParam<T> extends InParam {

    String outTradeNo();
    
    /**
     * 订单金额  单位元 保留两位小数点
     *
     * @return
     */
    String totalAmount();

    String subject();

    /**
     * 绝对超时时间 格式 yyyy-MM-dd HH:mm:ss
     *
     * @return
     */
    String timeExpire();

    /**
     * 回调地址
     *
     * @return
     */
    String notifyUrl();


    /**
     * 微信 openId
     *
     * @return
     */
    String openId();

    /**
     * 交易类型trade_type
     * JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，不同trade_type决定了调起支付的方式，请根据支付产品正确上传
     *
     * @return
     */
    WXTradeTypeEnum tradeType();
}
