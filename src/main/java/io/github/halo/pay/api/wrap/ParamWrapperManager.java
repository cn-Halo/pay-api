package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.*;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface ParamWrapperManager {

    ParamWrapper payParamWrapper(InParam param);

    FacePayParamWrapper facePayParamWrapper(FacePayParam payParam);

    OrderQueryParamWrapper orderQueryParamWrapper(OrderQueryParam queryParam);

    RefundParamWrapper refundParamWrapper(RefundParam refundParam);


    RefundQueryParamWrapper refundQueryParamWrapper(RefundQueryParam refundQueryParam);

    CloseParamWrapper closeParamWrapper(CloseParam closeParam);

    CancelParamWrapper cancelParamWrapper(CancelParam cancelParam);

    DownloadBillParamWrapper downloadBillParamWrapper(DownloadBillParam downloadBillParam);


}
