package io.github.halo.pay.api.resp;

/**
 * @author yzm
 * @date 2021/9/23 22:34
 */
public interface RefundQueryResp {

    String outTradeNo();

    /**
     * 交易流水号
     * 支付宝 trade_no
     * 微信 transaction_id
     *
     * @return
     */
    String tradeNo();

    /**
     * 订单总金额
     * 微信 total_fee
     * 支付宝 total_amount
     * @return
     */
    String totalAmount();

    /**
     * 退款次数
     * @return
     */
//    String refund_count();

    /**
     * 支付宝 out_request_no
     * 微信 out_refund_no_$n
     * 商户退款单号
     * @return
     */
    String outRefundNo();

    /**
     * 微信 refund_id
     *
     * @return
     */
//    String refundNo();

    /**
     * 申请退款金额
     * 支付宝 refund_amount
     * 微信 refund_fee_$n
     * @return
     */
    String refundFee();

    /**
     * 微信退款状态：refund_status_$n
     * SUCCESS—退款成功
     * REFUNDCLOSE—退款关闭，指商户发起退款失败的情况。
     * PROCESSING—退款处理中
     * CHANGE—退款异常，退款到银行发现用户的卡作废或者冻结了，导致原路退款银行卡失败，可前往商户平台（pay.weixin.qq.com）-交易中心，手动处理此笔退款。$n为下标，从0开始编号
     * <p>
     * <p>
     * 支付宝退款状态。枚举值：
     * REFUND_SUCCESS 退款处理成功；
     * 未返回该字段表示退款请求未收到或者退款失败；
     * 注：如果退款查询发起时间早于退款时间，或者间隔退款发起时间太短，可能出现退款查询时还没处理成功，后面又处理成功的情况，建议商户在退款发起后间隔10秒以上再发起退款查询请求。
     *
     * @return
     */
    String refundStatus();

    /**
     * 退款完成时间
     * 支付宝 gmt_refund_pay
     * 微信 refund_success_time_$n
     * @return
     */
    String gmtRefundPay();

}

