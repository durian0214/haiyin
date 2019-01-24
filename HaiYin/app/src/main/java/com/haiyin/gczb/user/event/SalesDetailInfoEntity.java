package com.haiyin.gczb.user.event;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/24.
 */
public class SalesDetailInfoEntity extends BaseEntity {

    /**
     * data : {"headImg":"头像","name":"张三 姓名","mobile":"130000000001 手机号","salesPosition":1,"cardNo":"银行账号","cardFrontPic":"证件正面照","cardBackPic":"证件反面照","backName":"银行名称","backAddress":"住址"}
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
         * headImg : 头像
         * name : 张三 姓名
         * mobile : 130000000001 手机号
         * salesPosition : 1  [职位 见常量定义]
         * cardNo : 银行账号
         * cardFrontPic : 证件正面照
         * cardBackPic : 证件反面照
         * backName : 银行名称
         * backAddress : 住址
         */

        private String headImg;
        private String name;
        private String mobile;
        private int salesPosition;
        private String cardNo;
        private String cardFrontPic;
        private String cardBackPic;
        private String backName;
        private String backAddress;

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public int getSalesPosition() {
            return salesPosition;
        }

        public void setSalesPosition(int salesPosition) {
            this.salesPosition = salesPosition;
        }

        public String getCardNo() {
            return cardNo;
        }

        public void setCardNo(String cardNo) {
            this.cardNo = cardNo;
        }

        public String getCardFrontPic() {
            return cardFrontPic;
        }

        public void setCardFrontPic(String cardFrontPic) {
            this.cardFrontPic = cardFrontPic;
        }

        public String getCardBackPic() {
            return cardBackPic;
        }

        public void setCardBackPic(String cardBackPic) {
            this.cardBackPic = cardBackPic;
        }

        public String getBackName() {
            return backName;
        }

        public void setBackName(String backName) {
            this.backName = backName;
        }

        public String getBackAddress() {
            return backAddress;
        }

        public void setBackAddress(String backAddress) {
            this.backAddress = backAddress;
        }
    }
}
