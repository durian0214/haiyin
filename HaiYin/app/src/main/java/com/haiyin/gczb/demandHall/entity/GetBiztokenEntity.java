package com.haiyin.gczb.demandHall.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/17.
 */
public class GetBiztokenEntity extends BaseEntity {


    /**
     * data : xxxxxxxx[biztoken，string]
     */

    private String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
