package io.github.halo.pay.api;

/**
 * Created on 2021/9/18.
 *
 * @author yzm
 */
public interface RefundParam<T> {

    String outTradeNo();

    String outRefundNo();

    String refundReason();

    String refundAmount();


}
