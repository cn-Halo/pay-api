package io.github.halo.pay.api.constant;

/**
 * Created on 2021/10/9.
 *
 * @author yzm
 */
public enum TradeStatusEnum {

    WAIT_BUYER_PAY("交易创建，等待买家付款"),
    TRADE_CLOSED("未付款交易超时关闭"),
    TRADE_SUCCESS("交易支付成功"),
    TRADE_FAILED("交易支付失败"),
    TRADE_REFUND("退款成功"),
    TRADE_FINISHED("交易结束，不可退款"),
    ;
    private String msg;

    TradeStatusEnum(String msg) {
        this.msg = msg;
    }

    public String msg() {
        return this.msg;
    }


}
