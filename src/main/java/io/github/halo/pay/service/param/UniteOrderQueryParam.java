package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * Created on 2021/10/9.
 *
 * @author yzm
 */
public interface UniteOrderQueryParam<T> extends OrderQueryParam<T> {

    PayTypeEnum payType();
}
