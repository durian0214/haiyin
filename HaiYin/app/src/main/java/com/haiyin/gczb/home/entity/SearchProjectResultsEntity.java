package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class SearchProjectResultsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * address : 北京市朝阳区
         * amount : 10000.0
         * projectId : 1082922337956929536
         * days : 10
         * title : 需要全屋订制家具
         * summary : 需要全屋订制家具中式风格
         * cityName : 北京市
         */

        private String address;
        private double amount;
        private String projectId;
        private int days;
        private String title;
        private String summary;
        private String cityName;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }
    }
}
