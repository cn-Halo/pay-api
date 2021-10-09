package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/24.
 *
 * @author yzm
 */
public interface FacePayResp extends PayResp {


    /**
     * 支付宝 trade_no
     * 微信 transaction_id
     *
     * @return
     */
    String tradeNo();

    String outTradeNo();

    /**
     * 支付宝 total_amount
     * 微信 total_fee
     *
     * @return
     */
    String totalAmount();

    /**
     * 微信 time_end yyyyMMddHHmmss
     * 支付宝 gmt_payment
     *
     * @return
     */
    String gmtPayment();


    /**
     * 交易状态
     *
     * @return
     */
    String tradeStatus();

    /**
     * 交易状态说明
     *
     * @return
     */
    String tradeStatusDesc();
}
