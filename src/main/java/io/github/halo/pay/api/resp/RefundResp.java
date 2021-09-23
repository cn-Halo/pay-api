package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public interface RefundResp {

    String tradeNo();

    String outTradeNo();

    String refundFee();//退款总金额。指该笔交易累计已经退款成功的金额。微信的待验证

}
