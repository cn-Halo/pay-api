package io.github.halo.pay.api;

/**
 * Created on 2021/6/26.
 *
 * @author yzm
 */
public interface PayApiResp<T> {

    String SUCCESS_CODE = "SUCCESS";

    String FAIL_CODE = "FAIL";

    //请求响应码
    PayApiResp code(String code);

    PayApiResp msg(String msg);

    //业务响应码
    PayApiResp subCode(String subCode);

    PayApiResp subMsg(String subMsg);

    PayApiResp attachment(T attachment);

}
