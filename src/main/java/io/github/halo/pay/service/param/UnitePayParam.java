package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.FacePayParam;
import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.api.param.WapPayParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * @author yzm
 * @date 2021/9/24 23:06
 */
public interface UnitePayParam extends FacePayParam, WapPayParam {

    PayTypeEnum payType();

}
