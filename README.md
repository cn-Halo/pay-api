
# 实现了微信、支付宝支付能力接口，并统一了下单，查询等接口

## 微信金额转换

## 微信订单转换

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

