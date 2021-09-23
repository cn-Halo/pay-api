package io.github.halo.pay.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

public class MathUtil {

    /**
     * 两数相除
     *
     * @param dividend 被除数
     * @param divisor  除数
     * @return result  商
     * <p>
     * BigDecimal.ROUND_UP;//进位处理，2.35变成2.4 BigDecimal.ROUND_DOWN;//直接删除多余的小数位，如2.35会变成2.3
     * BigDecimal.ROUND_HALF_UP;//四舍五入，2.35变成2.4 BigDecimal.ROUND_HALF_DOWN;//四舍五入，2.35变成2.3，如果是5则向下舍
     * ...
     */
    public static String divide(String dividend, String divisor, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(dividend);
        BigDecimal b2 = new BigDecimal(divisor);
        BigDecimal result = b1.divide(b2, scale, roundingMode);
        return result.toString();
    }

    /**
     * 两数相减
     *
     * @param minuend    被减数
     * @param subtrahend 减数
     * @return result 差
     */

    public static String subtract(String minuend, String subtrahend) {
        BigDecimal b1 = new BigDecimal(minuend);
        BigDecimal b2 = new BigDecimal(subtrahend);
        BigDecimal result = b1.subtract(b2);
        return result.toString();
    }

    /**
     * 保留小数位
     *
     * @param number 数字
     * @param scale  小数位数
     */
    public static String formatScale(String number, int scale, int roundingMode) {
        BigDecimal b1 = new BigDecimal(number);
        BigDecimal result = b1.setScale(scale, roundingMode);
        return result.toString();
    }

    /**
     * 分转换为元
     */
    public static String fenToYuan(String amount) {
        NumberFormat format = NumberFormat.getInstance();
        try {
            Number number = format.parse(amount);
            float temp = number.floatValue() / 100.0f;
//            format.setGroupingUsed(false);
//            //设置返回的小数部分所允许的最大位数
//            format.setMaximumFractionDigits(2);
//            amount = format.format(temp);
            return String.format("%.2f", temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return amount;
    }


    public static String yuanToFen(String amount) {
        NumberFormat format = NumberFormat.getInstance();
        try {
            Number number = format.parse(amount);
            double temp = number.doubleValue() * 100.0;
            format.setGroupingUsed(false);
            // 设置返回数的小数部分所允许的最大位数
            format.setMaximumFractionDigits(0);
            amount = format.format(temp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return amount;
    }

}
