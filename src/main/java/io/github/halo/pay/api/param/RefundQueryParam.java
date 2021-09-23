package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public interface RefundQueryParam<T> extends InParam {

    String outTradeNo();

    String outRefundNo();
}
