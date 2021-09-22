package io.github.halo.pay.api.resp;

import java.util.Map;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public class DefaultWXRespConvertManager<T> implements WXRespConvertManager<T> {

    @Override
    public WXRespConvert wapPayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };

    }

    @Override
    public WXRespConvert facePayRespConvert() {
        return new WXRespConvert<T>() {
            @Override
            public T convert(Map resp) {
                return null;
            }
        };
    }
}
