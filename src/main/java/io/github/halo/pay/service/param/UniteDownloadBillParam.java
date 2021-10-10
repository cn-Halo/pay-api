package io.github.halo.pay.service.param;

import io.github.halo.pay.api.param.DownloadBillParam;
import io.github.halo.pay.service.constant.PayTypeEnum;

/**
 * @author yzm
 * @date 2021/10/10 10:19
 */
public interface UniteDownloadBillParam<T> extends DownloadBillParam<T> {

    PayTypeEnum payType();
}
