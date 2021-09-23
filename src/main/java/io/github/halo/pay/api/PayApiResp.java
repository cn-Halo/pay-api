package io.github.halo.pay.api;

/**
 * Created on 2021/6/26.
 *
 * @author yzm
 */
public interface PayApiResp<T> {

    String SUCCESS_CODE = "SUCCESS";

    String FAIL_CODE = "FAIL";

    //网络请求响应码
    PayApiResp code(String code);

    PayApiResp msg(String msg);

    //业务请求响应码
    PayApiResp subCode(String subCode);

    PayApiResp subMsg(String subMsg);

    PayApiResp data(T data);

    PayApiResp attachment(Object attachment);


    String code();

    String msg();

    String subCode();

    String subMsg();

    T data();

    Object attachment();

}
