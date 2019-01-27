package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/18.
 */
public class UserEntity extends BaseEntity {

    /**
     * data : {"contactsPhone":"13000000003","businessLicensePic":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","industryName":"家具","corpCardBack":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","finaCardFront":"http://pri.oss.jiuniok.com/avatars/154746563200190160.jpg","memberPosition":1,"finaCardBack":"http://pri.oss.jiuniok.com/avatars/154746563548569863.jpg","idCardNo":"1111111","headImg":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","companyName":"曲美家具3","bankName":"哦了也扣","companyPhone":"13000000003","contactsName":"个体户3","corpCardFront":"http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png","companyId":"1084638284656812033","cardNo":"666"}
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
         * contactsPhone : 13000000003
         * businessLicensePic : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * industryName : 家具
         * corpCardBack : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * finaCardFront : http://pri.oss.jiuniok.com/avatars/154746563200190160.jpg
         * memberPosition : 1
         * finaCardBack : http://pri.oss.jiuniok.com/avatars/154746563548569863.jpg
         * idCardNo : 1111111
         * headImg : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * companyName : 曲美家具3
         * bankName : 哦了也扣
         * companyPhone : 13000000003
         * contactsName : 个体户3
         * corpCardFront : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         * companyId : 1084638284656812033
         * cardNo : 666
         */

        private String contactsPhone;
        private String businessLicensePic;
        private String industryName;
        private String corpCardBack;
        private String finaCardFront;
        private int memberPosition;
        private String finaCardBack;
        private String idCardNo;
        private String headImg;
        private String companyName;
        private String bankName;
        private String companyPhone;
        private String contactsName;
        private String corpCardFront;
        private String companyId;
        private String cardNo;
        private String finaName;

        public String getFinaName() {
            return finaName;
        }

        public void setFinaName(String finaName) {
            this.finaName = finaName;
        }

        public String getContactsPhone() {
            return contactsPhone;
        }

        public void setContactsPhone(String contactsPhone) {
            this.contactsPhone = contactsPhone;
        }

        public String getBusinessLicensePic() {
            return businessLicensePic;
        }

        public void setBusinessLicensePic(String businessLicensePic) {
            this.businessLicensePic = businessLicensePic;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getCorpCardBack() {
            return corpCardBack;
        }

        public void setCorpCardBack(String corpCardBack) {
            this.corpCardBack = corpCardBack;
        }

        public String getFinaCardFront() {
            return finaCardFront;
        }

        public void setFinaCardFront(String finaCardFront) {
            this.finaCardFront = finaCardFront;
        }

        public int getMemberPosition() {
            return memberPosition;
        }

        public void setMemberPosition(int memberPosition) {
            this.memberPosition = memberPosition;
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

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
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

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getContactsName() {
            return contactsName;
        }

        public void setContactsName(String contactsName) {
            this.contactsName = contactsName;
        }

        public String getCorpCardFront() {
            return corpCardFront;
        }

        public void setCorpCardFront(String corpCardFront) {
            this.corpCardFront = corpCardFront;
        }

        public String getCompanyId() {
            return companyId;
        }

        public void setCompanyId(String companyId) {
            this.companyId = companyId;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }
    }
}
