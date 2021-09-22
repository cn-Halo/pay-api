package io.github.halo.pay.api.impl;

import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.WXPayApi;
import io.github.halo.pay.api.wrap.FacePayParamWrapper;
import io.github.halo.pay.api.wrap.OrderQueryParamWrapper;
import io.github.halo.pay.api.wrap.UnifiedOrderParamWrapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created on 2021/6/8.
 * 实现原生的微信支付接口
 *
 * @author yzm
 */
@Slf4j
public abstract class AbstractWXPayApi implements WXPayApi {

    private WXPay wxPayClient;

    public AbstractWXPayApi(WXPay wxPayClient) {
        this.wxPayClient = wxPayClient;
    }

    /**
     * da.put("body", subject);
     * da.put("out_trade_no", outTradeNo);
     * da.put("total_fee", totalFee_fen);
     * da.put("spbill_create_ip", spbillCreateIp);
     * da.put("auth_code", authCode);
     * da.put("time_expire", timeExpire);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T, R extends Map> T microPay(FacePayParamWrapper<T, R> paramWrapper) throws Exception {
        Map da = paramWrapper.wrap();
        Map<String, String> resp = wxPayClient.microPay(da);//调用当面付接口
        return (T) resp;
    }

    /**
     * data.put("body", body);
     * data.put("out_trade_no", outTradeNo);
     * data.put("total_fee", totalFee);
     * data.put("spbill_create_ip", spbillCreateIp);
     * data.put("notify_url", notifyUrl);
     * data.put("trade_type", "JSAPI");
     * data.put("openid", openid);
     * data.put("time_expire", timeExpire);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T, R extends Map> T unifiedOrder(UnifiedOrderParamWrapper<T, R> paramWrapper) throws Exception {
        Map da = paramWrapper.wrap();
        Map<String, String> resp = wxPayClient.unifiedOrder(da);
        return (T) resp;
    }


    @Override
    public <T, R extends Map> T query0(OrderQueryParamWrapper<T, R> queryParam) throws Exception {
        Map da = queryParam.wrap();
        Map<String, String> resp = wxPayClient.orderQuery(da);
        return (T) resp;
    }
}
