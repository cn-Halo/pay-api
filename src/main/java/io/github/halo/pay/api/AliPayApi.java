package io.github.halo.pay.api;

import com.alipay.api.AlipayResponse;
import io.github.halo.pay.api.wrap.*;

/**
 * Created on 2021/6/8.
 * 原生的支付宝支付能力接口
 *
 * @author yzm
 */
public interface AliPayApi extends PayApi {

    /**
     * 手机网站支付
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T wapPay0(WapPayParamWrapper<T, R> paramWrapper) throws Exception;

    /**
     * 付款码支付
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T facePay0(FacePayParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 订单查询接口
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T query0(OrderQueryParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 退款接口
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T refund0(RefundParamWrapper<T, R> paramWrapper) throws Exception;

    /**
     * 退款查询接口
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T refundQuery0(RefundQueryParamWrapper<T, R> paramWrapper) throws Exception;

    /**
     * 撤销订单接口
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T cancel0(CancelParamWrapper<T, R> paramWrapper) throws Exception;

    /**
     * 关闭订单接口
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T close0(CloseParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 下单对账单地址
     *
     * @param billType     账单类型，商户通过接口或商户经开放平台授权后其所属服务商通过接口可以获取以下账单类型，支持：
     *                     trade：商户基于支付宝交易收单的业务账单；
     *                     signcustomer：基于商户支付宝余额收入及支出等资金变动的帐务账单。
     * @param billDate     账单时间：日账单格式为yyyy-MM-dd，最早可下载2016年1月1日开始的日账单；月账单格式为yyyy-MM，最早可下载2016年1月开始的月账单。
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T extends AlipayResponse, R extends String> T downloadBillUrl0(DownloadBillParamWrapper<T, R> paramWrapper) throws Exception;


}
