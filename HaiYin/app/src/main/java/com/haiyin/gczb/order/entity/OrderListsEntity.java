package com.haiyin.gczb.order.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/14.
 */
public class OrderListsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * projectId : 1081490503305928704
         * title : 标题
         * cityName : 城市
         * amount : 10000
         * summary : 简介
         * days : 6
         * address : 详细地址
         * projectStatus : 状态
         * invoiceFileEntity : 发票文件地址
         */

        private String projectId;
        private String title;
        private String cityName;
        private Double amount;
        private String summary;
        private int days;
        private String address;
        private String projectStatus;
        private String invoiceFileEntity;

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

        public Double getAmount() {
            return amount;
        }

        public void setAmount(Double amount) {
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

        public String getProjectStatus() {
            return projectStatus;
        }

        public void setProjectStatus(String projectStatus) {
            this.projectStatus = projectStatus;
        }

        public String getInvoiceFileEntity() {
            return invoiceFileEntity;
        }

        public void setInvoiceFileEntity(String invoiceFileEntity) {
            this.invoiceFileEntity = invoiceFileEntity;
        }
    }
}
