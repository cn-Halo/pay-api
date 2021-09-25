package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public interface OrderQueryResp extends PayResp {

    String tradeNo();

    String outTradeNo();

    String tradeStatus();

    String totalAmount();

//    String subject();//支付宝 选填

    //付款时间
    String gmtPayment();

//    String buyerLoginId();


}
