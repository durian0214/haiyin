package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/20.
 */
public class MyPagerProjectEntity extends BaseEntity {


    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * projectId : 1081490503305928704
         * title : 标题
         * cityName : 城市
         * amount : 10000
         * summary : 简介
         * days : 6
         * address : 详细地址
         * projectStatus : 6  [项目状态 6=待开票, 7=申请开票中,8=已开票完成]
         * invoiceFileCompany : 发票文件地址
         */

        private String projectId;
        private String title;
        private String cityName;
        private int amount;
        private String summary;
        private int days;
        private String address;
        private int projectStatus;
        private String invoiceFileCompany;

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getDays() {
            return days;
        }

        public void setDays(int days) {
            this.days = days;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getProjectStatus() {
            return projectStatus;
        }

        public void setProjectStatus(int projectStatus) {
            this.projectStatus = projectStatus;
        }

        public String getInvoiceFileCompany() {
            return invoiceFileCompany;
        }

        public void setInvoiceFileCompany(String invoiceFileCompany) {
            this.invoiceFileCompany = invoiceFileCompany;
        }
    }
}
