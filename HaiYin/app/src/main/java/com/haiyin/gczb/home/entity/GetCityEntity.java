package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/17.
 */
public class GetCityEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * name : 厦门市
         * cityId : 1080653077521661952
         */

        private String name;
        private String cityId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }
    }
}
