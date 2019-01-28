package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/28.
 */
public class PaysEntity extends BaseEntity {

    /**
     * data : {"totalAmount":10000,"arrivalAmount":9000,"unArrivalAmount":1000,"dataList":[{"projectId":"1081490503305928704","title":"标题","amount":10000,"createDate":"日期","isArrivaled":true}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * totalAmount : 10000
         * arrivalAmount : 9000
         * unArrivalAmount : 1000
         * dataList : [{"projectId":"1081490503305928704","title":"标题","amount":10000,"createDate":"日期","isArrivaled":true}]
         */

        private Double totalAmount;
        private Double arrivalAmount;
        private Double unArrivalAmount;
        private List<DataListBean> dataList;

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public Double getArrivalAmount() {
            return arrivalAmount;
        }

        public void setArrivalAmount(Double arrivalAmount) {
            this.arrivalAmount = arrivalAmount;
        }

        public Double getUnArrivalAmount() {
            return unArrivalAmount;
        }

        public void setUnArrivalAmount(Double unArrivalAmount) {
            this.unArrivalAmount = unArrivalAmount;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * projectId : 1081490503305928704
             * title : 标题
             * amount : 10000
             * createDate : 日期
             * isArrivaled : true
             */

            private String projectId;
            private String title;
            private int amount;
            private String createDate;
            private boolean isArrivaled;

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

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
            }

            public boolean isIsArrivaled() {
                return isArrivaled;
            }

            public void setIsArrivaled(boolean isArrivaled) {
                this.isArrivaled = isArrivaled;
            }
        }
    }
}
