package com.haiyin.gczb.demandHall.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/17.
 */
public class DetectInfoEntity extends BaseEntity {

    /**
     * data : {"errMsg":"Success[提示信息，string，成功：Success，其他：错误信息]","errorCode":0}
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
         * errMsg : Success[提示信息，string，成功：Success，其他：错误信息]
         * errorCode : 0  [人脸核身结果，int，0成功，其他失败]
         */

        private String errMsg;
        private int errorCode;

        public String getErrMsg() {
            return errMsg;
        }

        public void setErrMsg(String errMsg) {
            this.errMsg = errMsg;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public void setErrorCode(int errorCode) {
            this.errorCode = errorCode;
        }
    }
}
