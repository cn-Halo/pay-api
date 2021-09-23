package io.github.halo.pay.api.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import io.github.halo.pay.api.AliPayApi;
import io.github.halo.pay.api.wrap.*;

/**
 * Created on 2021/6/8.
 *
 * @author yzm
 */
public abstract class AbstractAliPayApi implements AliPayApi {

//    Logger log = Logger.getLogger(AliPayApi.class.getName());

    private AlipayClient alipayClient;

    public AbstractAliPayApi(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    /**
     * bizContent.put("out_trade_no", outTradeNo);
     * bizContent.put("total_amount", totalAmount);
     * bizContent.put("subject", subject);
     * bizContent.put("timeout_express", timeoutExpress);  //订单相对超时时间。该笔订单允许的最晚付款时间
     * bizContent.put("product_code", productCode);
     * bizContent.put("goods_type", goodsType);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends AlipayResponse, R extends String> T wapPay0(WapPayParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setNotifyUrl(paramWrapper.notifyUrl());//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent(bizContentStr);//填充业务参数
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayRequest);
        return (T) response;
    }

    /**
     * bizContent.put("out_trade_no", outTradeNo);
     * bizContent.put("scene", scene);
     * bizContent.put("auth_code", authCode);
     * bizContent.put("total_amount", totalAmount);
     * bizContent.put("subject", subject);
     * bizContent.put("operator_id", operatorId);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends AlipayResponse, R extends String> T facePay0(FacePayParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradePayRequest alipayRequest = new AlipayTradePayRequest();
        alipayRequest.setBizContent(bizContentStr);
        AlipayTradePayResponse response = alipayClient.execute(alipayRequest);
        return (T) response;
    }


    @Override
    public <T extends AlipayResponse, R extends String> T query0(OrderQueryParamWrapper<T, R> queryParam) throws Exception {
        String bizContentStr = queryParam.wrap();
        AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
        request.setBizContent(bizContentStr);
        AlipayTradeQueryResponse response = alipayClient.execute(request);
        return (T) response;
    }


    @Override
    public <T extends AlipayResponse, R extends String> T refund0(RefundParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        request.setBizContent(bizContentStr);
        AlipayTradeRefundResponse response = alipayClient.execute(request);
        return (T) response;
    }


    @Override
    public <T extends AlipayResponse, R extends String> T refundQuery0(RefundQueryParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
        request.setBizContent(bizContentStr);
        AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
        return (T) response;
    }

    @Override
    public <T extends AlipayResponse, R extends String> T cancel0(CancelParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        request.setBizContent(bizContentStr);
        AlipayTradeCancelResponse response = alipayClient.execute(request);
        return (T) response;
    }

    @Override
    public <T extends AlipayResponse, R extends String> T close0(CloseParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
        request.setBizContent(bizContentStr);
        AlipayTradeCloseResponse response = alipayClient.execute(request);
        return (T) response;
    }

    @Override
    public <T extends AlipayResponse, R extends String> T downloadBillUrl0(DownloadBillParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent(bizContentStr);
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
        return (T) response;
    }
}
