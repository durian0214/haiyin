package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/27.
 */
public class CardsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * bankCardId : 1082681449276121088
         * companyName : 公司名称
         * bankName : 银行信息
         * cardNo : 卡号
         * bankAddress : 开户行地址
         * taxNo : 纳税号
         * phone : 联系方式
         */

        private String bankCardId;
        private String companyName;
        private String bankName;
        private String cardNo;
        private String bankAddress;
        private String taxNo;
        private String phone;

        public String getBankCardId() {
            return bankCardId;
        }

        public void setBankCardId(String bankCardId) {
            this.bankCardId = bankCardId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getBankAddress() {
            return bankAddress;
        }

        public void setBankAddress(String bankAddress) {
            this.bankAddress = bankAddress;
        }

        public String getTaxNo() {
            return taxNo;
        }

        public void setTaxNo(String taxNo) {
            this.taxNo = taxNo;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
