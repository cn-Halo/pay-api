package io.github.halo.pay.api.resp;

import io.github.halo.pay.api.PayApiResp;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public class PayApiRespBuilder {

    private String code;
    private String msg;
    private String subCode;
    private String subMsg;
    private Object attachment;
    private Object data;


    private PayApiResp payApiResp;


    public static PayApiRespBuilder instance() {
        return new PayApiRespBuilder();
    }

    public static PayApiResp success() {
        return success(null, null);
    }

    public static PayApiResp success(Object data, Object attachment) {
        PayApiRespBuilder builder = instance();
        builder.code = PayApiResp.SUCCESS_CODE;
        builder.msg = "网络请求成功";
        builder.subCode = PayApiResp.SUCCESS_CODE;
        builder.subMsg = "业务请求成功";
        builder.data = data;
        builder.attachment = attachment;
        return builder.build();
    }


    public static PayApiResp subFail(String subMsg, Object attachment) {
        PayApiRespBuilder builder = instance();
        builder.code = PayApiResp.SUCCESS_CODE;
        builder.subCode = PayApiResp.FAIL_CODE;
        builder.subMsg = subMsg;
        builder.attachment = attachment;
        return builder.build();
    }

    public static PayApiResp subFail() {
        return subFail("业务请求失败", null);
    }

    public static PayApiResp subFail(Object attachment) {
        return subFail("业务请求失败", attachment);
    }


    public PayApiResp build() {
        return new DefaultPayApiResp()
                .code(code)
                .msg(msg)
                .subCode(subCode)
                .subMsg(subMsg)
                .data(data)
                .attachment(attachment);
    }


}
