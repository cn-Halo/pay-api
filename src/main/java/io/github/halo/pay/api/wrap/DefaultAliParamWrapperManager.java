package io.github.halo.pay.api.wrap;

import com.alibaba.fastjson.JSONObject;
import io.github.halo.pay.api.param.WapPayParam;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultAliParamWrapperManager implements AliParamWrapperManager {

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
}
