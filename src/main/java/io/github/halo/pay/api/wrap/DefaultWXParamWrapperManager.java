package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 2021/9/22.
 * <p>
 * 将统一入参转换成支付宝微信对应的入参
 *
 * @author yzm
 */
public class DefaultWXParamWrapperManager implements WXParamWrapperManager {
    @Override
    public UnifiedOrderParamWrapper unifiedOrderParamWrapper(WapPayParam wapPayParam) {

        UnifiedOrderParamWrapper unifiedOrderParamWrapper = new UnifiedOrderParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("body", wapPayParam.subject());
                da.put("out_trade_no", wapPayParam.outTradeNo());
                da.put("total_fee", wapPayParam.totalAmount());
//                da.put("spbill_create_ip", wapPayParam.totalAmount());
                da.put("notify_url", wapPayParam.notifyUrl());
//                da.put("trade_type", wapPayParam.notifyUrl());
                da.put("time_expire", wapPayParam.timeExpire());
//                da.put("openid",openid);
                return da;
            }
        };
        return unifiedOrderParamWrapper;
    }

    @Override
    public FacePayParamWrapper facePayParamWrapper(FacePayParam payParam) {

        FacePayParamWrapper facePayParamWrapper = new FacePayParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("body", payParam.subject());
                da.put("out_trade_no", payParam.outTradeNo());
                da.put("total_fee", payParam.totalAmount());
//                da.put("spbill_create_ip", payParam/);
                da.put("auth_code", payParam.authCode());
//                da.put("time_expire", payParam.timeExpire());
                return da;
            }
        };
        return facePayParamWrapper;
    }

    @Override
    public OrderQueryParamWrapper orderQueryParamWrapper(OrderQueryParam payParam) {
        OrderQueryParamWrapper orderQueryParamWrapper = new OrderQueryParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("transaction_id", payParam.tradeNo());
                da.put("out_trade_no", payParam.outTradeNo());
                return da;
            }
        };
        return orderQueryParamWrapper;
    }

    @Override
    public RefundParamWrapper refundParamWrapper(RefundParam refundParam) {
        RefundParamWrapper refundParamWrapper = new RefundParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("out_trade_no", refundParam.outTradeNo());
                da.put("out_refund_no", refundParam.outRefundNo());
//                da.put("total_fee", refundParam.refundAmount());
                da.put("refund_fee", refundParam.refundAmount());
                da.put("reason", refundParam.refundReason());
                return da;
            }
        };
        return refundParamWrapper;
    }

    @Override
    public RefundQueryParamWrapper refundQueryParamWrapper(RefundQueryParam refundQueryParam) {
        RefundQueryParamWrapper refundQueryParamWrapper = new RefundQueryParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("out_trade_no", refundQueryParam.outTradeNo());
                da.put("out_refund_no", refundQueryParam.outRefundNo());
                return da;
            }
        };
        return refundQueryParamWrapper;
    }

    @Override
    public CloseParamWrapper closeParamWrapper(CloseParam closeParam) {
        CloseParamWrapper closeParamWrapper = new CloseParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("out_trade_no", closeParam.outTradeNo());
                return da;
            }
        };
        return closeParamWrapper;
    }

    @Override
    public CancelParamWrapper cancelParamWrapper(CancelParam cancelParam) {
        CancelParamWrapper cancelParamWrapper = new CancelParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("out_trade_no", cancelParam.outTradeNo());
                return da;
            }
        };
        return cancelParamWrapper;
    }

    @Override
    public DownloadBillParamWrapper downloadBillParamWrapper(DownloadBillParam downloadBillParam) {
        DownloadBillParamWrapper downloadBillParamWrapper = new DownloadBillParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("bill_date", downloadBillParam.billDate());
                da.put("bill_type", downloadBillParam.billType());
                return da;
            }
        };
        return downloadBillParamWrapper;
    }
}
