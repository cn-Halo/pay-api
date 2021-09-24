package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface WapPayParam<T> extends InParam {

    String outTradeNo();

    String payType();

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

    String openId();


}
