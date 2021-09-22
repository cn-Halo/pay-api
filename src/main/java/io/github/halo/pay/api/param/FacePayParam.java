package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface FacePayParam<T> extends InParam {

    String outTradeNo();

    String totalAmount();

    String subject();

    String scene();

    String authCode();

//    String timeExpire();//支付宝中的timeout_express 而微信中是time_expire


}
