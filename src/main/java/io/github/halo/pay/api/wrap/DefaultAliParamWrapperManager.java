package io.github.halo.pay.api.wrap;

import com.alibaba.fastjson.JSONObject;
import io.github.halo.pay.api.param.*;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultAliParamWrapperManager implements AliParamWrapperManager {

    @Override
    public FacePayParamWrapper facePayParamWrapper(FacePayParam facePayParam) {

        FacePayParamWrapper facePayParamWrapper = new FacePayParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("out_trade_no", facePayParam.outTradeNo());
                jsonObject.put("scene", facePayParam.scene());
                jsonObject.put("auth_code", facePayParam.authCode());
                jsonObject.put("total_amount", facePayParam.totalAmount());
                jsonObject.put("subject", facePayParam.subject());
                return jsonObject.toString();
            }
        };
        return facePayParamWrapper;
    }

    @Override
    public WapPayParamWrapper wapPayParamWrapper(WapPayParam wapPayParam) {

        WapPayParamWrapper wapPayParamWrapper = new WapPayParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("out_trade_no", wapPayParam.outTradeNo());
                jsonObject.put("total_amount", wapPayParam.totalAmount());
                jsonObject.put("subject", wapPayParam.subject());
                jsonObject.put("time_expire", wapPayParam.timeExpire());
//                jsonObject.put("product_code",wapPayParam.totalAmount());
//                jsonObject.put("goods_type",wapPayParam.totalAmount());
                return jsonObject.toString();
            }

            @Override
            public String notifyUrl() {
                return wapPayParam.notifyUrl();
            }
        };

        return wapPayParamWrapper;
    }

    @Override
    public OrderQueryParamWrapper orderQueryParamWrapper(OrderQueryParam queryParam) {

        OrderQueryParamWrapper orderQueryParamWrapper = new OrderQueryParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("out_trade_no", queryParam.outTradeNo());
                jsonObject.put("trade_no", queryParam.tradeNo());
                return jsonObject.toString();
            }
        };
        return orderQueryParamWrapper;
    }

    @Override
    public RefundParamWrapper refundParamWrapper(RefundParam refundParam) {
        RefundParamWrapper refundParamWrapper = new RefundParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject bizContent = new JSONObject();
                bizContent.put("out_trade_no", refundParam.outTradeNo());
                bizContent.put("refund_amount", refundParam.refundAmount());
                bizContent.put("out_request_no", refundParam.outRefundNo());
                return bizContent.toString();
            }
        };
        return refundParamWrapper;
    }

    @Override
    public RefundQueryParamWrapper refundQueryParamWrapper(RefundQueryParam refundQueryParam) {
        RefundQueryParamWrapper refundQueryParamWrapper = new RefundQueryParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject bizContent = new JSONObject();
                bizContent.put("out_trade_no", refundQueryParam.outTradeNo());
                bizContent.put("out_request_no", refundQueryParam.outRefundNo());
                return bizContent.toString();
            }
        };
        return refundQueryParamWrapper;
    }

    @Override
    public CloseParamWrapper closeParamWrapper(CloseParam closeParam) {
        CloseParamWrapper closeParamWrapper = new CloseParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject bizContent = new JSONObject();
                bizContent.put("out_trade_no", closeParam.outTradeNo());
                return bizContent.toString();
            }
        };
        return closeParamWrapper;

    }

    @Override
    public CancelParamWrapper cancelParamWrapper(CancelParam cancelParam) {
        CancelParamWrapper cancelParamWrapper = new CancelParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject bizContent = new JSONObject();
                bizContent.put("out_trade_no", cancelParam.outTradeNo());
                return bizContent.toString();
            }
        };
        return cancelParamWrapper;
    }

    @Override
    public DownloadBillParamWrapper downloadBillParamWrapper(DownloadBillParam downloadBillParam) {
        DownloadBillParamWrapper downloadBillParamWrapper = new DownloadBillParamWrapper<Object, String>() {
            @Override
            public String wrap() {
                JSONObject bizContent = new JSONObject();
                bizContent.put("bill_type", downloadBillParam.billType());
                bizContent.put("bill_date", downloadBillParam.billDate());
                return bizContent.toString();
            }
        };
        return downloadBillParamWrapper;
    }
}
