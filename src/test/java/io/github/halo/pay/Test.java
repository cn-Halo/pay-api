package io.github.halo.pay;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import io.github.halo.pay.api.PayApi;
import io.github.halo.pay.api.PayApiResp;
import io.github.halo.pay.api.impl.AliPayApiImpl;
import io.github.halo.pay.api.param.OrderQueryParam;
import io.github.halo.pay.api.resp.OrderQueryResp;
import io.github.halo.pay.api.resp.PayResp;
import io.github.halo.pay.util.JsonUtil;

import java.lang.reflect.Method;

/**
 * Created on 2021/9/18.
 *
 * @author yzm
 */
public class Test {


    public static void main(String[] args) throws Exception {
        String serverUrl = "https://openapi.alipay.com/gateway.do";
        String aliAppId = "2017081008129491";
        String aliAppPrivateKey = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCfjR9zNNMqm5+9eonciF2p2pE2qSh4vRH65/dbVXzO3AcurW65RCWmDH4m/OaTltm0FHGVaQLkjGmrT6HMQvgJF2t/Zhin74PtggcwoUrTguw4K3gvFYu3yw+lo+14u9EjPH7uOmQvhdAG3hVpRsjQMUXG5iXDOm6ToE7ggzAfFuS9mj9/DJaeSO7GKhhR/Yh7PKKBkBOoBBmVeg2WuhkmSF0v9GKnOkPjX9qzu7dYYBP0PEqy+g8fzZgX0DmzjuwCv+uACYspP+DfnUlS86rcV13tAm5wDKxleKNcdBTVYg2rPr01Zxomtc1jECbbLR2ugOzwzpFLzk/DO0OBxWHDAgMBAAECggEBAJmCP0MqDQ4Oo3e7wY60iWGWEGdGA9F/ICCHNL/klWu12Rc4+LI59fs4Q9whMXvJBPc1QD9fxM++pSCVQnfz6w9cQCpYQdq6IPXQSDiqxwKQEdTNRhoPIdmY/xoAicfC9Spw1zNAbQooNgmVfYZH+4gMEDWZU7Xtr1xMPffXYOJXLcP0/MP0Cpaa99nomVtTooCUtX8pcLSuY2Ii6p3Fzohqq4J8RMBq1n06JlEqUAvgrYrsimh1fusdyzirxS1dWLhYj1AYILI4+fPXlIFJCfyEO4fICVL/nYXcdlgkndUfPArJOQKOTI9y49c2v0+PTioK/Au4TVyuTapTrvYL0MECgYEA5CgBAtSlPYX524z6TjDb4zPETBJvPuHbTP7eNG8cbIjvdVU75pinow/jwByMTKCWs9HBR40ldAa6898HTEzOCsx7g3+OpLH50e0pP+IHfvEYMw8lhMnC02PVMDQgKTCY+Z6butJwAK04HQIPjXMHUmSuiz1oGNiyVshjnPPro6ECgYEAswXHrgo4IO5a7aQVUVBVGRivuii7qnkUtMt+EMPekTaQmKXyP6JNwnyj0OnVx826SoZzy+I+s/NRBGRYFfWvkmOsKvAHAojztrzCH99z0T7xEfJWya95866wkybKDH9zLWIjX57AMkUK8QZpDCGvSyBn2jFvlbKUbP6OSk0PCuMCgYEAr22O8XU9pKOaLVUltO9ec0D/gxp9jRUugPJLOnfgnBdiP46M406nPvrdvH525ghoN9VlTrXZgXAXWaI4Q1Oe7bCym7Z9oGb0VpSLLgKGjvWkDMZtq79S3SUa69h4jhM+3Fg3F5vs4KhPyAYonTNAAvtFNey+WLRt08p6xVjL62ECgYEAqps/iGTEyN1eWkqc5jkgYCJelZJsBjINwZ6MLX38hXbypga/rlUGteoCU/4Edb1hY9RuVkbuap/e/ciDCapx/5htYx4MECZj3CpW9fkFbswFAY1pmBtapRg9vn6g3vg203FLNL0dfMq/eqMZSS5w16+ySQYDyS2+tYCCKdz2EPECgYAQW11sSF4Sh9h50JkWnylaqK9ZlBUgkDtOUHDz3S0p6rM/2gUs5CNy8x98R7gCuRubrzkdlQJ/QyRF+GHLpplMrJLwioSpPfR8qgHE87VQIbSbgEyRtKzgWCxbks99aexxXllhwjCTQcuu+HGMvlBd1xjuNGRSNQryitPfkSvwxQ==";
        String format = "json";
        String charset = "UTF-8";
        String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAj6NTh8SkJPzmRSLkDH8k5peJdRk+Lfl42ZmDyURogygrW7BzAHySDiu7lgFah4b2i2SSDDNcxEdCNmsVGA9Z+wIsgo9sNT0rDTrTQDerlmrZnJNoWzNDI3VAnfPg6m7gebMiB7PD5pq7oHfhbt6teSQKOEHK9UFY8KwmtNg4By7B6o5OMu2yBs3sS3VUi2ilZTHyFMIQZBYkUnunu1ItTlzj7Sl5cX+rBCJumrRIBYFhzaRdJbkRYE2MaWJib5ru8KSHjJR/rXovetJBO1XHrwjFf/dp2XmthNFRx6KkkeuecqI4cCoJHdS2EwO4T81f6/ScCzsAwgO1UJRaBzh77wIDAQAB";
        String aliSignType = "RSA2";

        AlipayClient alipayClient = new DefaultAlipayClient(serverUrl, aliAppId,
                aliAppPrivateKey,
                format, charset, alipayPublicKey, aliSignType);

        PayApi payApi = new AliPayApiImpl(alipayClient);

//        payApi.facePay(new FacePayParam<AlipayResponse>() {
//            @Override
//            public String outTradeNo() {
//                return "123";
//            }
//
//            @Override
//            public String totalAmount() {
//                return "0.01";
//            }
//
//            @Override
//            public String subject() {
//                return "测试";
//            }
//
//            @Override
//            public String scene() {
//                return "bar_code";
//            }
//
//            @Override
//            public String authCode() {
//                return "284832742165939547";
//            }
//        });

        PayApiResp<OrderQueryResp> payApiResp = (PayApiResp) payApi.query(new OrderQueryParam<Object>() {
            @Override
            public String tradeNo() {
                return null;
            }

            @Override
            public String outTradeNo() {
                return "123";
            }
        });
        System.out.println(payApiResp);
    }
}
