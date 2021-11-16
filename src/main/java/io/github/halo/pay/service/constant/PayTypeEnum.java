package io.github.halo.pay.service.constant;

/**
 * @author yzm
 * @date 2021/9/24 23:07
 */
public enum PayTypeEnum {

    WX("微信"),
    ALI("支付宝"),

    WX_WAP_PAY("微信WAP支付"),
    ALI_WAP_PAY("支付宝WAP支付"),

    WX_APP_PAY("微信APP支付"),
    ALI_APP_PAY("支付宝APP支付"),

    WX_FACE_PAY("微信当面付"),
    ALI_FACE_PAY("支付宝当面付");

    private String msg;

    PayTypeEnum(String msg) {
        this.msg = msg;
    }

    public String msg() {
        return this.msg;
    }

    public boolean isWX() {
        if (this.name().indexOf("WX") != -1)
            return true;
        return false;
    }

    public boolean isALI() {
        if (this.name().indexOf("ALI") != -1)
            return true;
        return false;
    }

    public boolean isFacePay() {
        if (this.name().indexOf("FACE") != -1)
            return true;
        return false;
    }

    public boolean isWapPay() {
        if (this.name().indexOf("WAP") != -1)
            return true;
        return false;
    }

    public boolean isAppPay() {
        if (this.name().indexOf("APP") != -1)
            return true;
        return false;
    }


}
