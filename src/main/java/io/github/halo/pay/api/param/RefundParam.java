package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/18.
 *
 * @author yzm
 */
public interface RefundParam<T> extends InParam {

    String outTradeNo();

    String outRefundNo();

    String refundReason();

    String refundAmount();


}
