package io.github.halo.pay.api.resp;

import io.github.halo.pay.api.PayApiResp;

/**
 * Created on 2021/6/26.
 *
 * @author yzm
 */
public class DefaultPayApiResp<T> implements PayApiResp<T> {

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private Object attachment;
    private T data;


    public static PayApiResp success() {
        return instance().code(SUCCESS_CODE).subCode(SUCCESS_CODE);
    }

    public static PayApiResp fail(String msg) {
        return instance().code(FAIL_CODE);
    }

    public static PayApiResp fail(String code, String msg) {
        return instance().code(FAIL_CODE);
    }

    public static PayApiResp subFail(String subMg) {
        return instance().code(SUCCESS_CODE).subCode(FAIL_CODE).subMsg(subMg);
    }

    public static PayApiResp subFail(String subCode, String subMg) {
        return instance().code(SUCCESS_CODE).subCode(subCode).subMsg(subMg);
    }

    public static PayApiResp instance() {
        return new DefaultPayApiResp();
    }

    @Override
    public PayApiResp code(String code) {
        this.code = code;
        return this;
    }

    @Override
    public PayApiResp msg(String msg) {
        this.msg = msg;
        return this;
    }

    @Override
    public PayApiResp subCode(String subCode) {
        this.subCode = subCode;
        return this;
    }

    @Override
    public PayApiResp subMsg(String subMsg) {
        this.subMsg = subMsg;
        return this;
    }

    @Override
    public PayApiResp data(T data) {
        this.data = data;
        return this;
    }

    @Override
    public PayApiResp attachment(Object attachment) {
        this.attachment = attachment;
        return this;
    }

    @Override
    public String code() {
        return this.code;
    }

    @Override
    public String msg() {
        return this.msg;
    }

    @Override
    public String subCode() {
        return this.subCode;
    }

    @Override
    public String subMsg() {
        return this.subMsg;
    }

    @Override
    public T data() {
        return this.data;
    }

    @Override
    public Object attachment() {
        return this.attachment;
    }


    @Override
    public String toString() {
        return "DefaultPayApiResp{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", subCode='" + subCode + '\'' +
                ", subMsg='" + subMsg + '\'' +
                ", attachment=" + attachment +
                ", data=" + data +
                '}';
    }
}
