package io.github.halo.pay.api.resp;

import io.github.halo.pay.api.resp.RespConvert;

import java.util.Map;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface WXRespConvert<R> extends RespConvert<Map<String, String>, R> {

    @Override
    R convert(Map<String, String> resp);
}
