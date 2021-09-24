package io.github.halo.pay.api.resp;

/**
 * Created on 2021/9/24.
 *
 * @author yzm
 */
public interface CancelResp {

    /**
     * 是否需要继续调用撤销，Y-需要，N-不需要
     * 微信 recall
     * 支付宝 retry_flag
     * Y/N
     *
     * @return
     */
    String recall();
}
