package io.github.halo.pay.api.resp;

import io.github.halo.pay.api.PayApiResp;
import io.github.halo.pay.util.JsonUtil;

import java.lang.reflect.Method;

/**
 * Created on 2021/6/26.
 *
 * @author yzm
 */
public class DefaultPayApiResp implements PayApiResp<PayResp> {

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private Object attachment;
    private PayResp data;


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
    public PayApiResp data(PayResp data) {
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
    public PayResp data() {
        return this.data;
    }

    @Override
    public Object attachment() {
        return this.attachment;
    }


    @Override
    public String toString() {
        //使用gson打印不出data 可能是匿名内部类的原因
        Method[] methods = null;
        String c = null;
        if (this.data != null && (methods = this.data.getClass().getDeclaredMethods()).length > 0) {
            String s = "{\r\n";
            for (Method method : methods) {
                method.setAccessible(true);
                String name = method.getName();
                Object value = null;
                try {
                    value = method.invoke(this.data);
                } catch (Exception e) {
                }
                s += "\t\t\"" + name + "\": " + "\"" + value + "\",\r\n";
            }
            s += "}\r\n";
            c = s;
        }
        String dataStr = "\t\"data\': " + c;
        String str = JsonUtil.toJson(this);
        if (str == null) {
            return null;
        }
        int idx = str.lastIndexOf("}");
        return str.substring(0, idx) + dataStr + "}\r\n";

    }
}
