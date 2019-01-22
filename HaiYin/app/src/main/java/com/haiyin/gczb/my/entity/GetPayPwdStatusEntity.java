package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/18.
 */
public class GetPayPwdStatusEntity extends BaseEntity {

    /**
     * data : 1
     */

    private int data;//[状态 1=表示已设置 2=表示未设置]

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
