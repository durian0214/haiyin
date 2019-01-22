package com.haiyin.gczb.base;

import java.io.Serializable;

/**
 * Created
 * by durian
 * 2018/4/12.
 */
public class BaseEntity implements Serializable {

    /**
     * em : 短信验证码发送成功，请查看短信
     * ec : 200
     * data : null
     */

    private String em;
    private int ec;
    public String getEm() {
        return em;
    }

    public void setEm(String em) {
        this.em = em;
    }

    public int getEc() {
        return ec;
    }

    public void setEc(int ec) {
        this.ec = ec;
    }


}
