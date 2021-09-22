package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.WapPayParam;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface AliParamWrapperManager extends ParamWrapperManager {

    WapPayParamWrapper wapPayParamWrapper(WapPayParam wapPayParam);
}
