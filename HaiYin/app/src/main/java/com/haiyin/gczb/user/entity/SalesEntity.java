package com.haiyin.gczb.user.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/21.
 */
public class SalesEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 张三
         * salesId : 1080653077521661952
         */

        private String name;
        private String salesId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSalesId() {
            return salesId;
        }

        public void setSalesId(String salesId) {
            this.salesId = salesId;
        }
    }
}
