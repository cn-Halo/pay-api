package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface WapPayParam<T> extends InParam {

    String outTradeNo();

    String payType();

    String totalAmount();

    String subject();

    String timeExpire();

    String notifyUrl();


}
