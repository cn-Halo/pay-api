package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.CancelParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * @author yzm
 * @date 2021/10/10 10:15
 */
public interface UniteCancelParam<T> extends CancelParam<T> {

    PayTypeEnum payType();

}
