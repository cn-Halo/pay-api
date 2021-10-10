package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.RefundQueryParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * @author yzm
 * @date 2021/10/10 09:26
 */
public interface UniteRefundQueryParam<T> extends RefundQueryParam<T> {

    PayTypeEnum payType();
}
