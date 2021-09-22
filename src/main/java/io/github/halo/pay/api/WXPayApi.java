package io.github.halo.pay.api;

import io.github.halo.pay.api.wrap.FacePayParamWrapper;
import io.github.halo.pay.api.wrap.OrderQueryParamWrapper;
import io.github.halo.pay.api.wrap.UnifiedOrderParamWrapper;

import java.util.Map;

/**
 * Created on 2021/6/8.
 * 原生的微信支付能力接口
 *
 * @author yzm
 */
public interface WXPayApi extends PayApi {

    /**
     * 付款码支付
     * <p>
     * 收银员使用扫码设备读取微信用户付款码以后，二维码或条码信息会传送至商户收银台，由商户收银台或者商户后台调用该接口发起支付。
     * <p>
     * 提醒1：提交支付请求后微信会同步返回支付结果。当返回结果为“系统错误”时，商户系统等待5秒后调用【查询订单API】，查询支付实际交易结果；当返回结果为“USERPAYING”时，商户系统可设置间隔时间(建议10秒)重新查询支付结果，直到支付成功或超时(建议45秒)；
     * <p>
     * 提醒2：在调用查询接口返回后，如果交易状况不明晰，请调用【撤销订单API】，此时如果交易失败则关闭订单，该单不能再支付成功；如果交易成功，则将扣款退回到用户账户。当撤销无返回或错误时，请再次调用。注意：请勿扣款后立即调用【撤销订单API】,建议至少15秒后再调用。撤销订单API需要双向证书。
     * <p>
     * https://pay.weixin.qq.com/wiki/doc/api/micropay.php?chapter=9_10&index=1
     * <p>
     * 用户付款码条形码规则：18位纯数字，以10、11、12、13、14、15开头
     * <p>
     *
     * @param paramWrapper
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T, R extends Map> T microPay(FacePayParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 统一下单接口
     * JSAPI支付下单
     * H5下单
     * native支付下单
     * <p>
     * 应用场景
     * JSAPI支付适用于线下场所、公众号场景和PC网站场景。
     */
    <T, R extends Map> T unifiedOrder(UnifiedOrderParamWrapper<T, R> paramWrapper) throws Exception;


    /**
     * 订单查询接口
     *
     * @param queryParam
     * @param <T>
     * @param <R>
     * @return
     * @throws Exception
     */
    <T, R extends Map> T query0(OrderQueryParamWrapper<T, R> queryParam) throws Exception;

}
