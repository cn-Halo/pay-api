package io.github.halo.pay.api.resp;


import com.alipay.api.AlipayResponse;
import io.github.halo.pay.api.resp.RespConvert;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface AliRespConvert<R> extends RespConvert<AlipayResponse, R> {

    @Override
    R convert(AlipayResponse resp);
}
