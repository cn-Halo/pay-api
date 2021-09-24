package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.*;
import io.github.halo.pay.util.DateUtil;
import io.github.halo.pay.util.MathUtil;

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
    /**
     * 交易类型trade_type
     * JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付，不同trade_type决定了调起支付的方式，请根据支付产品正确上传
     *
     * @param wapPayParam
     * @return
     */
    @Override
    public UnifiedOrderParamWrapper unifiedOrderParamWrapper(WapPayParam wapPayParam) {

        UnifiedOrderParamWrapper unifiedOrderParamWrapper = new UnifiedOrderParamWrapper<Object, Map<String, String>>() {
            @Override
            public Map wrap() {
                Map<String, String> da = new HashMap<>();
                da.put("body", wapPayParam.subject());
                da.put("out_trade_no", wapPayParam.outTradeNo());
                da.put("total_fee", wapPayParam.totalAmount() != null ? MathUtil.yuanToFen(wapPayParam.totalAmount()) : null);
//                da.put("spbill_create_ip", wapPayParam.totalAmount());
                da.put("notify_url", wapPayParam.notifyUrl());
//                da.put("trade_type", wapPayParam.notifyUrl());
                da.put("time_expire", wapPayParam.timeExpire() != null ? DateUtil.string2String(wapPayParam.timeExpire(), DateUtil.FULL_FORMAT, DateUtil.YYYYMMDDHHMMSS_FORMAT) : null); //入参格式
                da.put("openid", wapPayParam.openId());
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
                da.put("total_fee", payParam.totalAmount() != null ? MathUtil.yuanToFen(payParam.totalAmount()) : null);
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
                da.put("refund_fee", refundParam.refundAmount() != null ? MathUtil.yuanToFen(refundParam.refundAmount()) : null);
                da.put("reason", refundParam.refundReason());//todo 微信旧版并无此字段
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
                da.put("bill_date", DateUtil.string2String(downloadBillParam.billDate(), DateUtil.YMD_FORMAT, DateUtil.YYYYMMDD_FORMAT));//微信格式 20140603
                da.put("bill_type", downloadBillParam.billType());
                return da;
            }
        };
        return downloadBillParamWrapper;
    }
}
