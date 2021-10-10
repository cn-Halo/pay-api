package io.github.halo.pay;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConfig;
import io.github.halo.pay.api.PayApiResp;
import io.github.halo.pay.api.constant.WXTradeTypeEnum;
import io.github.halo.pay.api.resp.OrderQueryResp;
import io.github.halo.pay.service.PayApiService;
import io.github.halo.pay.service.constant.PayTypeEnum;
import io.github.halo.pay.service.param.UniteOrderQueryParam;
import io.github.halo.pay.service.param.UnitePayParam;

import java.io.InputStream;

/**
 * @author yzm
 * @date 2021/10/10 10:38
 */
public class Test {


    public WXPay createWXClient() {
        WXPayConfig wxPayConfig = new WXPayConfig() {
            @Override
            public String getAppID() {
                return null;
            }

            @Override
            public String getMchID() {
                return null;
            }

            @Override
            public String getKey() {//API 密钥 apiKey
                return null;
            }

            @Override
            public InputStream getCertStream() {
                return null;
            }

            @Override
            public int getHttpConnectTimeoutMs() {
                return 0;
            }

            @Override
            public int getHttpReadTimeoutMs() {
                return 0;
            }
        };
        WXPay wxpay = new WXPay(wxPayConfig);
        return wxpay;
    }

    public AlipayClient createAlipayClient() {
        String serverUrl = "https://openapi.alipay.com/gateway.do";
        String aliAppId = "";
        String aliAppPrivateKey = "";
        String format = "json";
        String charset = "UTF-8";
        String alipayPublicKey = "";
        String aliSignType = "RSA2";

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, aliAppId,
                aliAppPrivateKey,
                format, charset, alipayPublicKey, aliSignType);
        return alipayClient;
    }


    public void pay() throws Exception {
        WXPay wxPayClient = createWXClient();
        AlipayClient alipayClient = createAlipayClient();
        PayApiService payApiService = new PayApiService(alipayClient, wxPayClient);
        PayApiResp payApiResp = payApiService.pay(new UnitePayParam() {
            @Override
            public PayTypeEnum payType() {
                return PayTypeEnum.ALI_FACE_PAY;
            }

            @Override
            public String outTradeNo() {
                return null;
            }

            @Override
            public String totalAmount() {
                return null;
            }

            @Override
            public String subject() {
                return null;
            }

            @Override
            public String scene() {
                return null;
            }

            @Override
            public String authCode() {
                return null;
            }

            @Override
            public String timeExpire() {
                return null;
            }

            @Override
            public String notifyUrl() {
                return null;
            }

            @Override
            public String openId() {
                return null;
            }

            @Override
            public WXTradeTypeEnum tradeType() {
                return null;
            }
        });

        if (payApiResp.isSuccess()) {
            //根据payTyp不同得到不同的响应体
            System.out.println(payApiResp.data());
        } else {
            System.out.println("调用失败 " + payApiResp.printMsg());
        }

    }

    public void query() throws Exception {
        WXPay wxPayClient = createWXClient();
        AlipayClient alipayClient = createAlipayClient();
        PayApiService payApiService = new PayApiService(alipayClient, wxPayClient);
        PayApiResp<OrderQueryResp> payApiResp = payApiService.query(new UniteOrderQueryParam<PayApiResp<OrderQueryResp>>() {
            @Override
            public PayTypeEnum payType() {
                return PayTypeEnum.ALI;
            }

            @Override
            public String tradeNo() {
                return null;
            }

            @Override
            public String outTradeNo() {
                return null;
            }
        });
        if (payApiResp.isSuccess()) {
            //根据订单状态判断
            System.out.println(payApiResp.data().tradeStatusDesc());

        } else {
            System.out.println("调用失败 " + payApiResp.printMsg());
        }

    }
}
