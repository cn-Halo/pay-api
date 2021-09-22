package io.github.halo.pay.api;

import com.alipay.api.AlipayResponse;
import io.github.halo.pay.api.wrap.FacePayParamWrapper;
import io.github.halo.pay.api.wrap.WapPayParamWrapper;

/**
 * Created on 2021/6/8.
 * 原生的支付宝支付能力接口
 *
 * @author yzm
 */
public interface AliPayApi extends PayApi {

    /**
     * 手机网站支付
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T wapPay0(WapPayParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 付款码支付
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T facePay0(FacePayParamWrapper<T, R> paramWrapper) throws Exception;


}
