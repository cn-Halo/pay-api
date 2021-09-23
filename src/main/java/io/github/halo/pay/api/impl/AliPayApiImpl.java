package io.github.halo.pay.api.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.response.*;
import io.github.halo.pay.api.param.*;
import io.github.halo.pay.api.resp.AliRespConvertManager;
import io.github.halo.pay.api.resp.DefaultAliRespConvertManager;
import io.github.halo.pay.api.wrap.*;

/**
 * Created on 2021/6/8.
 *
 * @author yzm
 */
public class AliPayApiImpl extends AbstractAliPayApi {

    private AliParamWrapperManager aliParamWrapperManager = new DefaultAliParamWrapperManager();
    private AliRespConvertManager aliRespConvertManager = new DefaultAliRespConvertManager();

    private AlipayClient alipayClient;

    public AliPayApiImpl(AlipayClient alipayClient) {
        super(alipayClient);
    }

    @Override
    public <T> T facePay(FacePayParam<T> facePayParam) throws Exception {
        FacePayParamWrapper facePayParamWrapper = aliParamWrapperManager.facePayParamWrapper(facePayParam);
        AlipayTradePayResponse response = (AlipayTradePayResponse) super.facePay0(facePayParamWrapper);
        return (T) aliRespConvertManager.facePayRespConvert().convert(response);
    }

    @Override
    public <T> T pay(PayParam<T> payParam) throws Exception {
        return null;
    }

    @Override
    public <T> T query(OrderQueryParam<T> orderQueryParam) throws Exception {
        OrderQueryParamWrapper orderQueryParamWrapper = aliParamWrapperManager.orderQueryParamWrapper(orderQueryParam);
        AlipayTradeQueryResponse response = (AlipayTradeQueryResponse) super.query0(orderQueryParamWrapper);
        return (T) aliRespConvertManager.orderQueryRespConvert().convert(response);
    }

    @Override
    public <T> T refund(RefundParam<T> refundParam) throws Exception {
        RefundParamWrapper refundParamWrapper = aliParamWrapperManager.refundParamWrapper(refundParam);
        AlipayTradeRefundResponse response = (AlipayTradeRefundResponse) super.refund0(refundParamWrapper);
        return (T) aliRespConvertManager.refundRespConvert().convert(response);
    }

    @Override
    public <T> T refundQuery(RefundQueryParam<T> refundQueryParam) throws Exception {
        RefundQueryParamWrapper refundQueryParamWrapper = aliParamWrapperManager.refundQueryParamWrapper(refundQueryParam);
        AlipayTradeFastpayRefundQueryResponse response = (AlipayTradeFastpayRefundQueryResponse) super.refundQuery0(refundQueryParamWrapper);
        return (T) aliRespConvertManager.refundQueryRespConvert().convert(response);
    }

    @Override
    public <T> T close(CloseParam closeParam) throws Exception {
        CloseParamWrapper closeParamWrapper = aliParamWrapperManager.closeParamWrapper(closeParam);
        AlipayTradeCloseResponse response = (AlipayTradeCloseResponse) super.close0(closeParamWrapper);
        return (T) aliRespConvertManager.closeRespConvert().convert(response);
    }

    @Override
    public <T> T cancel(CancelParam<T> cancelParam) throws Exception {
        CancelParamWrapper cancelParamWrapper = aliParamWrapperManager.cancelParamWrapper(cancelParam);
        AlipayTradeCancelResponse response = (AlipayTradeCancelResponse) super.cancel0(cancelParamWrapper);
        return (T) aliRespConvertManager.cancelRespConvert().convert(response);
    }

    @Override
    public <T> T downloadBill(DownloadBillParam<T> downloadBillParam) throws Exception {
        DownloadBillParamWrapper downloadBillParamWrapper = aliParamWrapperManager.downloadBillParamWrapper(downloadBillParam);
        AlipayDataDataserviceBillDownloadurlQueryResponse response = (AlipayDataDataserviceBillDownloadurlQueryResponse) super.downloadBillUrl0(downloadBillParamWrapper);
        return (T) aliRespConvertManager.downloadBillRespConvert().convert(response);
    }


    @Override
    public <T> T wapPay(WapPayParam<T> payParam) throws Exception {
        WapPayParamWrapper wapPayParamWrapper = aliParamWrapperManager.wapPayParamWrapper(payParam);
        AlipayTradeWapPayResponse alipayTradeWapPayResponse = (AlipayTradeWapPayResponse) super.wapPay0(wapPayParamWrapper);
        return (T) aliRespConvertManager.wapPayRespConvert().convert(alipayTradeWapPayResponse);
    }

    public void setParamWrapperManager(AliParamWrapperManager paramWrapperManager) {
        this.aliParamWrapperManager = paramWrapperManager;
    }

    public void setRespConvertManager(AliRespConvertManager respConvertManager) {
        this.aliRespConvertManager = respConvertManager;
    }


}
