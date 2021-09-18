package io.github.halo.pay.api.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayDataDataserviceBillDownloadurlQueryRequest;
import com.alipay.api.request.AlipayTradeCancelRequest;
import com.alipay.api.response.AlipayDataDataserviceBillDownloadurlQueryResponse;
import com.alipay.api.response.AlipayTradeCancelResponse;
import io.github.halo.pay.api.AliPayApi;
import io.github.halo.pay.api.CancelParam;
import io.github.halo.pay.api.PayParam;
import io.github.halo.pay.api.QueryParam;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2021/6/8.
 *
 * @author yzm
 */
@Slf4j
public  class AliPayApiImpl implements AliPayApi {

//    Logger log = Logger.getLogger(AliPayApi.class.getName());

    private AlipayClient alipayClient;

    public AliPayApiImpl(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    @Override
    public <T> T pay(PayParam<T> payParam) throws Exception {
        return null;
    }

    @Override
    public <T> T query(QueryParam<T> queryParam) throws Exception {
        return null;
    }

    @Override
    public Object refund(String outTradeNo) throws Exception {
        return null;
    }

    @Override
    public Object refundQuery(String outTradeNo) throws Exception {
        return null;
    }

    @Override
    public Object close(String outTradeNo) throws Exception {
        return null;
    }

    /**
     * 查询对账单下载地址
     *
     * @param billType   账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型，支持：
     *                   trade：商户基于支付宝交易收单的业务账单；
     *                   signcustomer：基于商户支付宝余额收入及支出等资金变动的帐务账单。
     * @param billDate   账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。
     * @param
     * @return
     */
    @Override
    public Object downloadBill(String billDate, String billType) throws Exception {

        AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        request.setBizContent("{" +
                "\"bill_type\":\"" + billType + "\"," +
                "\"bill_date\":\"" + billDate + "\"" +
                "  }");
        AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
//        if (response == null || !response.isSuccess()) {
////            log.se("【下载支付宝对账单】 下载失败 ：{}", response);
//            throw new ValidateException(PayCodeMsg.ERROR_PROMPT, response.getSubMsg());
//        }
//        return response.getBillDownloadUrl();
        return response;
    }

    //PayApiResp<CancelResp>
    @Override
    public Object cancel(CancelParam cancelParam) throws Exception {
        AlipayTradeCancelRequest request = new AlipayTradeCancelRequest();
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", cancelParam.outTradeNo());
        request.setBizContent(bizContent.toString());
        AlipayTradeCancelResponse response = alipayClient.execute(request);
        return response;
    }


}