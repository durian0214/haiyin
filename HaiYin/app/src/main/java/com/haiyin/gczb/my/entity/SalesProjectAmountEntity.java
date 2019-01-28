package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/25.
 */
public class SalesProjectAmountEntity extends BaseEntity {

    /**
     * data : {"totalAmount":90000,"dataList":[{"companyId":"1082681449276121088","title":"标题","amount":10000,"createDate":"2019.01.09"}]}
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
         * totalAmount : 90000
         * dataList : [{"companyId":"1082681449276121088","title":"标题","amount":10000,"createDate":"2019.01.09"}]
         */

        private Double totalAmount;
        private List<DataListBean> dataList;

        public Double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<DataListBean> getDataList() {
            return dataList;
        }

        public void setDataList(List<DataListBean> dataList) {
            this.dataList = dataList;
        }

        public static class DataListBean {
            /**
             * companyId : 1082681449276121088
             * title : 标题
             * amount : 10000.0
             * createDate : 2019.01.09
             */

            private String companyId;
            private String title;
            private double amount;
            private String createDate;

            public String getCompanyId() {
                return companyId;
            }

            public void setCompanyId(String companyId) {
                this.companyId = companyId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public double getAmount() {
                return amount;
            }

            public void setAmount(double amount) {
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
