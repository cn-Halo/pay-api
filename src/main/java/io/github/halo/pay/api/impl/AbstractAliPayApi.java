package io.github.halo.pay.api.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.request.AlipayTradePayRequest;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradePayResponse;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import io.github.halo.pay.api.AliPayApi;
import io.github.halo.pay.api.wrap.FacePayParamWrapper;
import io.github.halo.pay.api.wrap.WapPayParamWrapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Created on 2021/6/8.
 *
 * @author yzm
 */
@Slf4j
public abstract class AbstractAliPayApi implements AliPayApi {

//    Logger log = Logger.getLogger(AliPayApi.class.getName());

    private AlipayClient alipayClient;

    public AbstractAliPayApi(AlipayClient alipayClient) {
        this.alipayClient = alipayClient;
    }

    /**
     * bizContent.put("out_trade_no", outTradeNo);
     * bizContent.put("total_amount", totalAmount);
     * bizContent.put("subject", subject);
     * bizContent.put("timeout_express", timeoutExpress);  //订单相对超时时间。该笔订单允许的最晚付款时间
     * bizContent.put("product_code", productCode);
     * bizContent.put("goods_type", goodsType);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends AlipayResponse, R extends String> T wapPay0(WapPayParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradeWapPayRequest alipayRequest = new AlipayTradeWapPayRequest();//创建API对应的request
        alipayRequest.setNotifyUrl(paramWrapper.notifyUrl());//在公共参数中设置回跳和通知地址
        alipayRequest.setBizContent(bizContentStr);//填充业务参数
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(alipayRequest);
        return (T) response;
    }

    /**
     * bizContent.put("out_trade_no", outTradeNo);
     * bizContent.put("scene", scene);
     * bizContent.put("auth_code", authCode);
     * bizContent.put("total_amount", totalAmount);
     * bizContent.put("subject", subject);
     * bizContent.put("operator_id", operatorId);
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    @Override
    public <T extends AlipayResponse, R extends String> T facePay0(FacePayParamWrapper<T, R> paramWrapper) throws Exception {
        String bizContentStr = paramWrapper.wrap();
        AlipayTradePayRequest alipayRequest = new AlipayTradePayRequest();
        alipayRequest.setBizContent(bizContentStr);
        AlipayTradePayResponse response = alipayClient.execute(alipayRequest);
        return (T) response;
    }


}
