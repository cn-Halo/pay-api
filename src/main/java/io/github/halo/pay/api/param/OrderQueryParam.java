package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/18.
 *
 * @author yzm
 */
public interface OrderQueryParam<T> extends InParam {

    String tradeNo();

    String outTradeNo();

}
