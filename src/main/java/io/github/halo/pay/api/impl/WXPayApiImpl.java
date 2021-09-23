package io.github.halo.pay.api.impl;

import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.param.*;
import io.github.halo.pay.api.resp.DefaultWXRespConvertManager;
import io.github.halo.pay.api.resp.WXRespConvertManager;
import io.github.halo.pay.api.wrap.*;

import java.util.Map;

/**
 * Created on 2021/6/8.
 * 负责实现统一的支付能力接口
 *
 * @author yzm
 */

public class WXPayApiImpl extends AbstractWXPayApi {

    private WXParamWrapperManager wxParamWrapperManager = new DefaultWXParamWrapperManager();
    private WXRespConvertManager wxRespConvertManager = new DefaultWXRespConvertManager();

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
        OrderQueryParamWrapper queryParamWrapper = wxParamWrapperManager.orderQueryParamWrapper(orderQueryParam);
        Map<String, String> resp = (Map<String, String>) super.query0(queryParamWrapper);
        return (T) wxRespConvertManager.orderQueryRespConvert().convert(resp);
    }

    @Override
    public <T> T refund(RefundParam<T> refundParam) throws Exception {
        RefundParamWrapper refundParamWrapper = wxParamWrapperManager.refundParamWrapper(refundParam);
        Map<String, String> resp = (Map<String, String>) super.refund0(refundParamWrapper);
        return (T) wxRespConvertManager.refundRespConvert().convert(resp);
    }

    @Override
    public <T> T refundQuery(RefundQueryParam<T> refundQueryParam) throws Exception {
        RefundQueryParamWrapper refundQueryParamWrapper = wxParamWrapperManager.refundQueryParamWrapper(refundQueryParam);
        Map<String, String> resp = (Map<String, String>) super.refundQuery0(refundQueryParamWrapper);
        return (T) wxRespConvertManager.refundQueryRespConvert().convert(resp);
    }

    @Override
    public <T> T close(CloseParam closeParam) throws Exception {
        CloseParamWrapper closeParamWrapper = wxParamWrapperManager.closeParamWrapper(closeParam);
        Map<String, String> resp = (Map<String, String>) super.close0(closeParamWrapper);
        return (T) wxRespConvertManager.closeRespConvert().convert(resp);
    }


    @Override
    public <T> T cancel(CancelParam<T> cancelParam) throws Exception {
        CancelParamWrapper cancelParamWrapper = wxParamWrapperManager.cancelParamWrapper(cancelParam);
        Map<String, String> resp = (Map<String, String>) super.cancel0(cancelParamWrapper);
        return (T) wxRespConvertManager.cancelRespConvert().convert(resp);
    }

    @Override
    public <T> T downloadBill(DownloadBillParam<T> downloadBillParam) throws Exception {
        DownloadBillParamWrapper downloadBillParamWrapper = wxParamWrapperManager.downloadBillParamWrapper(downloadBillParam);
        Map<String, String> resp = (Map<String, String>) super.downloadBill0(downloadBillParamWrapper);
        return (T) wxRespConvertManager.downloadBillRespConvert().convert(resp);
    }

    public void setParamWrapperManager(WXParamWrapperManager wxParamWrapperManager) {
        this.wxParamWrapperManager = wxParamWrapperManager;
    }

    public void setRespConvertManager(WXRespConvertManager respConvertManager) {
        this.wxRespConvertManager = respConvertManager;
    }


}
