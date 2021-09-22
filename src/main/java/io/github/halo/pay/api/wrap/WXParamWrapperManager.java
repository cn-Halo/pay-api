package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.FacePayParam;
import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.api.param.WapPayParam;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface WXParamWrapperManager extends ParamWrapperManager {

    UnifiedOrderParamWrapper unifiedOrderParamWrapper(WapPayParam wapPayParam);

    FacePayParamWrapper facePayParamWrapper(FacePayParam payParam);

    OrderQueryParamWrapper facePayParamWrapper(OrderQueryParam payParam);

}
