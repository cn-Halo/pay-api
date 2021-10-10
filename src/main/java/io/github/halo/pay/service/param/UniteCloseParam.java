package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.CloseParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * @author yzm
 * @date 2021/10/10 09:33
 */
public interface UniteCloseParam<T> extends CloseParam<T> {
    PayTypeEnum payType();
}
