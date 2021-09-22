package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.FacePayParam;
import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.api.param.RefundParam;
import io.github.halo.pay.api.param.WapPayParam;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface AliParamWrapperManager extends ParamWrapperManager {

    FacePayParamWrapper facePayParamWrapper(FacePayParam wapPayParam);

    WapPayParamWrapper wapPayParamWrapper(WapPayParam wapPayParam);

    OrderQueryParamWrapper orderQueryParamWrapper(OrderQueryParam queryParam);

    RefundParamWrapper refundParamWrapper(RefundParam refundParam);
}
