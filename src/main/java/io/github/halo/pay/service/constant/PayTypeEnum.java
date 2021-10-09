package io.github.halo.pay.service.constant;

/**
 * @author yzm
 * @date 2021/9/24 23:07
 */
public enum PayTypeEnum {

    WX,
    ALI,

    WX_WAP_PAY,
    ALI_WAP_PAY,

    WX_APP_PAY,
    ALI_APP_PAY,

    WX_FACE_PAY,
    ALI_FACE_PAY;

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
