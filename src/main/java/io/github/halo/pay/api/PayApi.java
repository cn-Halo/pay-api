package io.github.halo.pay.api;

import io.github.halo.pay.api.param.*;

/**
 * Created on 2021/6/8.
 * <p>
 * 统一的支付能力接口
 * <p>
 * 所有的异常全部抛出 给调用方捕捉
 *
 * @author yzm
 */
public interface PayApi extends Capacity {


    <T> T wapPay(WapPayParam<T> payParam) throws Exception;


    <T> T facePay(FacePayParam<T> payParam) throws Exception;


    /**
     * 统一下单接口
     *
     * @return
     * @throws Exception
     */
    <T> T pay(PayParam<T> payParam) throws Exception;

    /**
     * 统一查询订单接口
     *
     * @param orderQueryParam
     * @param <T>
     * @return
     * @throws Exception
     */
    <T> T query(OrderQueryParam<T> orderQueryParam) throws Exception;


    /**
     * 统一退款接口
     *
     * @param refundParam
     * @param <T>
     * @return
     * @throws Exception
     */

    <T> T refund(RefundParam<T> refundParam) throws Exception;


    /**
     * 退款查询接口
     *
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    Object refundQuery(String outTradeNo) throws Exception;


    /**
     * 统一关闭订单接口
     *
     * @param outTradeNo
     * @return
     * @throws Exception
     */
    Object close(String outTradeNo) throws Exception;


    /**
     * 统一撤销订单接口
     *
     * @param
     * @return
     */
    <T> T cancel(CancelParam<T> cancelParam) throws Exception;


    /**
     * 下载对账单
     *
     * @param billType 账单类型
     * @param billDate 账单日期 yyyy-MM-dd
     * @return
     * @throws Exception
     */
    Object downloadBill(String billDate, String billType) throws Exception;


}
