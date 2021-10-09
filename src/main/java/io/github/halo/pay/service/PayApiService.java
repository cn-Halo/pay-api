package io.github.halo.pay.service;

import com.alipay.api.AlipayClient;
import com.github.wxpay.sdk.WXPay;
import io.github.halo.pay.api.PayApi;
import io.github.halo.pay.api.PayApiResp;
import io.github.halo.pay.api.impl.AliPayApiImpl;
import io.github.halo.pay.api.impl.WXPayApiImpl;
import io.github.halo.pay.api.resp.OrderQueryResp;
import io.github.halo.pay.service.constant.ALITradeStatus;
import io.github.halo.pay.service.constant.TradeStatusEnum;
import io.github.halo.pay.service.constant.WXTradeStatus;
import io.github.halo.pay.service.param.UniteOrderQueryParam;
import io.github.halo.pay.service.param.UnitePayParam;


/**
 * @author yzm
 * @date 2021/9/24 22:44
 */
public class PayApiService {
    private PayApi aliPayApi;
    private PayApi wxPayApi;

    public PayApiService(AlipayClient alipayClient, WXPay wxPayClient) {
        this.aliPayApi = new AliPayApiImpl(alipayClient);
        this.wxPayApi = new WXPayApiImpl(wxPayClient);
    }

    public <T> T pay(UnitePayParam payParam) throws Exception {
        if (payParam.payType() == null)
            throw new NullPointerException("payType is null");

        if (payParam.payType().isALI() && payParam.payType().isWapPay()) {
            return (T) aliPayApi.wapPay(payParam);
        }
        if (payParam.payType().isALI() && payParam.payType().isFacePay()) {
            return (T) aliPayApi.facePay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isWapPay()) {
            return (T) wxPayApi.wapPay(payParam);
        }

        if (payParam.payType().isWX() && payParam.payType().isFacePay()) {
            return (T) wxPayApi.facePay(payParam);
        }
        throw new UnsupportedOperationException("unsupported payType");
    }


    public void query(UniteOrderQueryParam<PayApiResp<OrderQueryResp>> uniteOrderQueryParam) throws Exception {
        if (uniteOrderQueryParam.payType() == null)
            throw new NullPointerException("payType is null");
        PayApiResp<OrderQueryResp> payApiResp = null;
        String tradeStatusConvert = null;
        if (uniteOrderQueryParam.payType().isALI() && (payApiResp = aliPayApi.query(uniteOrderQueryParam)).isSuccess()) {
            String tradeStatus = payApiResp.data().tradeStatus();
            switch (tradeStatus) {
                case ALITradeStatus.WAIT_BUYER_PAY://交易创建，等待买家付款
                    tradeStatusConvert = TradeStatusEnum.WAIT_BUYER_PAY.name();
                    break;
                case ALITradeStatus.TRADE_CLOSED://未付款交易超时关闭，或支付完成后全额退款
                    tradeStatusConvert = TradeStatusEnum.TRADE_CLOSED.name();
                    break;
                case ALITradeStatus.TRADE_SUCCESS://交易支付成功
                    tradeStatusConvert = TradeStatusEnum.TRADE_SUCCESS.name();
                    break;
                case ALITradeStatus.TRADE_FINISHED://交易结束，不可退款
                    tradeStatusConvert = TradeStatusEnum.TRADE_FINISHED.name();
                    break;
            }
        }

        if (uniteOrderQueryParam.payType().isWX() && (payApiResp = wxPayApi.query(uniteOrderQueryParam)).isSuccess()) {
            String tradeStatus = payApiResp.data().tradeStatus();
            switch (tradeStatus) {

                case WXTradeStatus.SUCCESS://支付成功
                    tradeStatusConvert = TradeStatusEnum.TRADE_SUCCESS.name();
                    break;

                case WXTradeStatus.REFUND://转入退款
                    //todo根据微信支付状态机，REFUND表示已退款，表明已经支付成功
                    tradeStatusConvert = TradeStatusEnum.TRADE_SUCCESS.name();
                    break;

                case WXTradeStatus.NOTPAY://未支付
                    tradeStatusConvert = TradeStatusEnum.WAIT_BUYER_PAY.name();
                    break;

                case WXTradeStatus.CLOSED://已关闭
                    tradeStatusConvert = TradeStatusEnum.TRADE_CLOSED.name();
                    break;

                case WXTradeStatus.REVOKED://已撤销(刷卡支付)
                    //无法处理此状态
//                    tradeStatusConvert = TradeStatusEnum.TRADE_CLOSED.name();
                    break;

                case WXTradeStatus.USERPAYING://-用户支付中
                    tradeStatusConvert = TradeStatusEnum.WAIT_BUYER_PAY.name();
                    break;

                case WXTradeStatus.PAYERROR://-支付失败(其他原因，如银行返回失败)
                    tradeStatusConvert = TradeStatusEnum.TRADE_FAILED.name();
                    break;

                case WXTradeStatus.ACCEPT://已接收，等待扣款
                    tradeStatusConvert = TradeStatusEnum.WAIT_BUYER_PAY.name();
                    break;
            }


        }


    }


}
