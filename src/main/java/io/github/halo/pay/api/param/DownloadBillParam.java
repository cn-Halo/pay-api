package io.github.halo.pay.api.param;

/**
 * Created on 2021/9/23.
 *
 * @author yzm
 */
public interface DownloadBillParam<T> extends InParam {

    String billType();

    /**
     * 账单日期 格式 yyyy-MM-dd
     *
     * @return
     */
    String billDate();
}
