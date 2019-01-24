package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class SalesCompanyDetailEntity extends BaseEntity {

    /**
     * data : {"companyId":"1082882518090391552","companyName":"曲美家具","companyPhone":"13000000001","headImg":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","contactsName":"张三","contactsPhone":"130000000001","memberPosition":1,"industryName":"家具","businessLicensePic":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","corpCardFront":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","corpCardBack":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","finaName":"张三","cardNo":"622221000033234433","bankName":"工商银行","finaCardFront":"","finaCardBack":"","idCardNo":"收款人身份证号"}
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
         * companyId : 1082882518090391552
         * companyName : 曲美家具
         * companyPhone : 13000000001
         * headImg : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * contactsName : 张三
         * contactsPhone : 130000000001
         * memberPosition : 1
         * industryName : 家具
         * businessLicensePic : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * corpCardFront : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * corpCardBack : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * finaName : 张三
         * cardNo : 622221000033234433
         * bankName : 工商银行
         * finaCardFront :
         * finaCardBack :
         * idCardNo : 收款人身份证号
         */

        private String companyId;
        private String companyName;
        private String companyPhone;
        private String headImg;
        private String contactsName;
        private String contactsPhone;
        private int memberPosition;
        private String industryName;
        private String businessLicensePic;
        private String corpCardFront;
        private String corpCardBack;
        private String finaName;
        private String cardNo;
        private String bankName;
        private String finaCardFront;
        private String finaCardBack;
        private String idCardNo;

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getContactsName() {
            return contactsName;
        }

        public void setContactsName(String contactsName) {
            this.contactsName = contactsName;
        }

        public String getContactsPhone() {
            return contactsPhone;
        }

        public void setContactsPhone(String contactsPhone) {
            this.contactsPhone = contactsPhone;
        }

        public int getMemberPosition() {
            return memberPosition;
        }

        public void setMemberPosition(int memberPosition) {
            this.memberPosition = memberPosition;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getBusinessLicensePic() {
            return businessLicensePic;
        }

        public void setBusinessLicensePic(String businessLicensePic) {
            this.businessLicensePic = businessLicensePic;
        }

        public String getCorpCardFront() {
            return corpCardFront;
        }

        public void setCorpCardFront(String corpCardFront) {
            this.corpCardFront = corpCardFront;
        }

        public String getCorpCardBack() {
            return corpCardBack;
        }

        public void setCorpCardBack(String corpCardBack) {
            this.corpCardBack = corpCardBack;
        }

        public String getFinaName() {
            return finaName;
        }

        public void setFinaName(String finaName) {
            this.finaName = finaName;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getFinaCardFront() {
            return finaCardFront;
        }

        public void setFinaCardFront(String finaCardFront) {
            this.finaCardFront = finaCardFront;
        }

        public String getFinaCardBack() {
            return finaCardBack;
        }

        public void setFinaCardBack(String finaCardBack) {
            this.finaCardBack = finaCardBack;
        }

        public String getIdCardNo() {
            return idCardNo;
        }

        public void setIdCardNo(String idCardNo) {
            this.idCardNo = idCardNo;
        }
    }
}
