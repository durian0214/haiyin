package com.haiyin.gczb.demandHall.entity;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class SignProjectEntity extends BaseEntity {

    /**
     * data : {"frameSignPdf":"网签框架合同文件","orderSignPdf":"网签订单合同","orderClearSignPdf":"网签项目结算单"}
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
         * frameSignPdf : 网签框架合同文件
         * orderSignPdf : 网签订单合同
         * orderClearSignPdf : 网签项目结算单
         */

        private String frameSignPdf;
        private String orderSignPdf;
        private String orderClearSignPdf;

        public String getFrameSignPdf() {
            return frameSignPdf;
        }

        public void setFrameSignPdf(String frameSignPdf) {
            this.frameSignPdf = frameSignPdf;
        }

        public String getOrderSignPdf() {
            return orderSignPdf;
        }

        public void setOrderSignPdf(String orderSignPdf) {
            this.orderSignPdf = orderSignPdf;
        }

        public String getOrderClearSignPdf() {
            return orderClearSignPdf;
        }

        public void setOrderClearSignPdf(String orderClearSignPdf) {
            this.orderClearSignPdf = orderClearSignPdf;
        }
    }
}
