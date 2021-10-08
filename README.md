
# 实现了微信、支付宝支付能力接口，并统一了下单，查询等接口

## TODO
- 微信金额转换
- 微信订单转换
- payApi接口返回的tradeStatus是支付渠道原生的内容,一：通过payApiService来实现订单状态的统一。二：直接在payApi接口中统一转换。
- PayApiService更上层的一种抽象。

## 架构图
![架构图](./pay-api架构图.png)

## 用法
### 统一支付接口
```
    public void pay() throws Exception {
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

        PayApi payApi = new AliPayApiImpl(alipayClient);

        payApi.facePay(new FacePayParam<AlipayResponse>() {
            @Override
            public String outTradeNo() {
                return "123";
            }

            @Override
            public String totalAmount() {
                return "0.01";
            }

            @Override
            public String subject() {
                return "测试";
            }

            @Override
            public String scene() {
                return "bar_code";
            }

            @Override
            public String authCode() {
                return "284832742165939547";
            }
        });

    }
```
### 查询接口
```
    public void query() throws Exception {
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

        PayApi payApi = new AliPayApiImpl(alipayClient);

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

```

