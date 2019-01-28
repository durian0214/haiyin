package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/28.
 */

public class ReceivedPaysEntity extends BaseEntity {

    /**
     * data : {"receivedAmount":"累积金额","dataList":[{"projectId":"1081490503305928704","title":"标题","amount":10000,"createDate":"日期"}]}
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
         * receivedAmount : 累积金额
         * dataList : [{"projectId":"1081490503305928704","title":"标题","amount":10000,"createDate":"日期"}]
         */

        private Double receivedAmount;
        private List<DataListBean> dataList;

        public Double getReceivedAmount() {
            return receivedAmount;
        }

        public void setReceivedAmount(Double receivedAmount) {
            this.receivedAmount = receivedAmount;
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
             */

            private String projectId;
            private String title;
            private int amount;
            private String createDate;

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
        }
    }
}

