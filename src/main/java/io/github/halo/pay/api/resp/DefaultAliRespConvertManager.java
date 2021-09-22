package io.github.halo.pay.api.resp;

import com.alipay.api.AlipayResponse;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultAliRespConvertManager<T> implements AliRespConvertManager<T> {

    @Override
    public AliRespConvert wapPayRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }

    @Override
    public AliRespConvert facePayRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }

    @Override
    public AliRespConvert orderQueryRespConvert() {
        return new AliRespConvert<T>() {
            @Override
            public T convert(AlipayResponse resp) {
                return null;
            }
        };
    }
}
