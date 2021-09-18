package io.github.halo.pay;

import com.alipay.api.AlipayResponse;
import io.github.halo.pay.api.CancelParam;
import io.github.halo.pay.api.PayApi;
import io.github.halo.pay.api.impl.AliPayApiImpl;

/**
 * Created on 2021/9/18.
 *
 * @author yzm
 */
public class Test {


    public static void main(String[] args) throws Exception {
        PayApi payApi = new AliPayApiImpl(null);


        AlipayResponse alipayResponse = payApi.cancel(new CancelParam<AlipayResponse>() {
            @Override
            public String outTradeNo() {
                return null;
            }
        });
    }
}
