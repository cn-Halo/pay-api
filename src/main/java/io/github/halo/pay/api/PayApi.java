package io.github.halo.pay.api;

/**
 * Created on 2021/6/8.
 * <p>
 * 支付能力接口，不包含业务
 * <p>
 * 所有的异常全部抛出 给调用方捕捉
 *
 * @author yzm
 */
public interface PayApi extends Capacity {


    /**
     * 下载对账单
     *
     * @param billType   账单类型
     * @param billDate   账单日期 yyyy-MM-dd
     * @param merchantId 商户号
     * @return
     * @throws Exception
     */
    Object downloadBill(String billType, String billDate, String merchantId) throws Exception;

    /**
     * 统一撤销订单接口
     *
     * @param outTradeNo
     * @return
     */
    PayApiResp cancel(String outTradeNo) throws Exception;

}
