package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface RespConvert<I, R> {
    R convert(I resp);
}
