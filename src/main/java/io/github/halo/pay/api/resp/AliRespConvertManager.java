package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/22.
 *
 * @author yzm
 */
public interface AliRespConvertManager<T> extends RespConvertManager {

    AliRespConvert wapPayRespConvert();

    AliRespConvert facePayRespConvert();

    AliRespConvert orderQueryRespConvert();

    AliRespConvert refundRespConvert();

    AliRespConvert refundQueryRespConvert();

    AliRespConvert closeRespConvert();

    AliRespConvert cancelRespConvert();

    AliRespConvert downloadBillRespConvert();


}
