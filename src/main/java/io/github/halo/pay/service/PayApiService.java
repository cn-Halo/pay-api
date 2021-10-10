package io.github.halo.pay.service;

import com.alipay.api.AlipayClient;
import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.PayApi;
import io.github.halo.pay.api.PayApiResp;
import io.github.halo.pay.api.impl.AliPayApiImpl;
import io.github.halo.pay.api.impl.WXPayApiImpl;
import io.github.halo.pay.api.resp.*;
import io.github.halo.pay.service.param.*;


/**
 * @author yzm
 * @date 2021/9/24 22:44
 */
public class PayApiService {
    private PayApi aliPayApi;
    private PayApi wxPayApi;

    public PayApiService(AlipayClient alipayClient, WXPay wxPayClient) {
        this.aliPayApi = new AliPayApiImpl(alipayClient);
        this.wxPayApi = new WXPayApiImpl(wxPayClient);
    }

    public PayApiResp pay(UnitePayParam<PayApiResp> payParam) throws Exception {
        if (payParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (payParam.payType().isALI() && payParam.payType().isWapPay()) {
            return aliPayApi.wapPay(payParam);
        }
        if (payParam.payType().isALI() && payParam.payType().isFacePay()) {
            return aliPayApi.facePay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isWapPay()) {
            return wxPayApi.wapPay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isFacePay()) {
            return wxPayApi.facePay(payParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + payParam.payType());
    }


    public PayApiResp<OrderQueryResp> query(UniteOrderQueryParam<PayApiResp<OrderQueryResp>> uniteOrderQueryParam) throws Exception {
        if (uniteOrderQueryParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (uniteOrderQueryParam.payType().isALI()) {
            return aliPayApi.query(uniteOrderQueryParam);
        }

        if (uniteOrderQueryParam.payType().isWX()) {
            return wxPayApi.query(uniteOrderQueryParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + uniteOrderQueryParam.payType());
    }

    public PayApiResp<RefundQueryResp> refundQuery(UniteRefundQueryParam<PayApiResp<RefundQueryResp>> uniteRefundQueryParam) throws Exception {
        if (uniteRefundQueryParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (uniteRefundQueryParam.payType().isALI()) {
            return aliPayApi.refundQuery(uniteRefundQueryParam);
        }

        if (uniteRefundQueryParam.payType().isWX()) {
            return wxPayApi.refundQuery(uniteRefundQueryParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + uniteRefundQueryParam.payType());
    }


    public PayApiResp<CloseResp> close(UniteCloseParam<PayApiResp<CloseResp>> uniteCloseParam) throws Exception {
        if (uniteCloseParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (uniteCloseParam.payType().isALI()) {
            return aliPayApi.close(uniteCloseParam);
        }

        if (uniteCloseParam.payType().isWX()) {
            return wxPayApi.close(uniteCloseParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + uniteCloseParam.payType());
    }

    public PayApiResp<CancelResp> cancel(UniteCancelParam<PayApiResp<CancelResp>> uniteCancelParam) throws Exception {
        if (uniteCancelParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (uniteCancelParam.payType().isALI()) {
            return aliPayApi.cancel(uniteCancelParam);
        }

        if (uniteCancelParam.payType().isWX()) {
            return wxPayApi.cancel(uniteCancelParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + uniteCancelParam.payType());
    }

    public PayApiResp<DownloadBillResp> downloadBill(UniteDownloadBillParam<PayApiResp<DownloadBillResp>> uniteDownloadBillParam) throws Exception {
        if (uniteDownloadBillParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (uniteDownloadBillParam.payType().isALI()) {
            return aliPayApi.downloadBill(uniteDownloadBillParam);
        }

        if (uniteDownloadBillParam.payType().isWX()) {
            return wxPayApi.downloadBill(uniteDownloadBillParam);
        }
        throw new UnsupportedOperationException("unsupported payType：" + uniteDownloadBillParam.payType());
    }


}
