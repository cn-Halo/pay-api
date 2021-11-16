package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public interface RefundResp extends PayResp {

    String refundNo();

    String outTradeNo();

    /**
     * 支付宝 退款总金额。指该笔交易累计已经退款成功的金额。
     * todo 微信的待验证 表示的是单臂订单退款金额
     *
     * @return
     */
    String refundFee();

}
