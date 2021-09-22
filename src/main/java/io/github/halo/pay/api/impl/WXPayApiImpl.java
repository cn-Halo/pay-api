package io.github.halo.pay.api.impl;

import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.param.*;
import io.github.halo.pay.api.resp.WXRespConvertManager;
import io.github.halo.pay.api.wrap.FacePayParamWrapper;
import io.github.halo.pay.api.wrap.UnifiedOrderParamWrapper;
import io.github.halo.pay.api.wrap.WXParamWrapperManager;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Created on 2021/6/8.
 * 负责实现统一的支付能力接口
 *
 * @author yzm
 */
@Slf4j
public class WXPayApiImpl extends AbstractWXPayApi {

    private WXParamWrapperManager wxParamWrapperManager;
    private WXRespConvertManager wxRespConvertManager;

    public WXPayApiImpl(WXPay wxPayClient) {
        super(wxPayClient);
    }

    @Override
    public <T> T wapPay(WapPayParam<T> payParam) throws Exception {
        UnifiedOrderParamWrapper unifiedOrderParamWrapper = wxParamWrapperManager.unifiedOrderParamWrapper(payParam);
        Map<String, String> resp = (Map<String, String>) super.unifiedOrder(unifiedOrderParamWrapper);
        return (T) wxRespConvertManager.wapPayRespConvert().convert(resp);
    }

    @Override
    public <T> T facePay(FacePayParam<T> payParam) throws Exception {
        FacePayParamWrapper facePayParamWrapper = wxParamWrapperManager.facePayParamWrapper(payParam);
        Map<String, String> resp = (Map<String, String>) super.microPay(facePayParamWrapper);
        return (T) wxRespConvertManager.facePayRespConvert().convert(resp);
    }

    @Override
    public <T> T pay(PayParam<T> payParam) throws Exception {
        return null;
    }

    @Override
    public <T> T query(OrderQueryParam<T> orderQueryParam) throws Exception {

        return super.query0();
    }

    @Override
    public <T> T refund(RefundParam<T> refundParam) throws Exception {
        return null;
    }

    @Override
    public Object refundQuery(String outTradeNo) throws Exception {
        return null;
    }

    @Override
    public Object close(String outTradeNo) throws Exception {
        return null;
    }

    @Override
    public <T> T cancel(CancelParam<T> cancelParam) throws Exception {
        return null;
    }

    @Override
    public Object downloadBill(String billDate, String billType) throws Exception {
        return null;
    }


    public void setParamWrapperManager(WXParamWrapperManager wxParamWrapperManager) {
        this.wxParamWrapperManager = wxParamWrapperManager;
    }

}
