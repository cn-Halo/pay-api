package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/22.
 * <p>
 *
 * @author yzm
 */
public interface WXRespConvertManager<T> extends RespConvertManager {

    WXRespConvert wapPayRespConvert();

    WXRespConvert facePayRespConvert();


}