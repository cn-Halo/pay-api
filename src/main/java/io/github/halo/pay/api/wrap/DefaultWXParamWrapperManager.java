package io.github.halo.pay.api.wrap;

import io.github.halo.pay.api.param.FacePayParam;
import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.api.param.WapPayParam;

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
                Map<String, String> map = new HashMap<>();
                map.put("body", wapPayParam.subject());
                map.put("out_trade_no", wapPayParam.outTradeNo());
                map.put("total_fee", wapPayParam.totalAmount());
//                map.put("spbill_create_ip", wapPayParam.totalAmount());
                map.put("notify_url", wapPayParam.notifyUrl());
//                map.put("trade_type", wapPayParam.notifyUrl());
                map.put("time_expire", wapPayParam.timeExpire());
//                map.put("openid",openid);
                return map;
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
                da.put("time_expire", payParam.timeExpire());
                return da;
            }
        };
        return facePayParamWrapper;
    }

    @Override
    public OrderQueryParamWrapper facePayParamWrapper(OrderQueryParam payParam) {
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
}
