package io.github.halo.pay.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yzm
 * @date 2021/7/5 11:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelResp {

    /**
     * 有无退款
     */
    private Boolean hasRefund;

    /**
     * 退款时间
     * hasRefund 为true的情况下 gmtRefundPay才可能有值
     */
    private String gmtRefundPay;



}
