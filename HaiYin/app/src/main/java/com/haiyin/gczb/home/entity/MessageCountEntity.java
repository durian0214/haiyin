package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class MessageCountEntity extends BaseEntity {


    /**
     * data : {"totalCount":3,"systemMsgCount":0,"walletMsgCount":2,"taskMsgCount":1}
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
         * totalCount : 3
         * systemMsgCount : 0
         * walletMsgCount : 2
         * taskMsgCount : 1
         */

        private int totalCount;
        private int systemMsgCount;
        private int walletMsgCount;
        private int taskMsgCount;

        public int getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getSystemMsgCount() {
            return systemMsgCount;
        }

        public void setSystemMsgCount(int systemMsgCount) {
            this.systemMsgCount = systemMsgCount;
        }

        public int getWalletMsgCount() {
            return walletMsgCount;
        }

        public void setWalletMsgCount(int walletMsgCount) {
            this.walletMsgCount = walletMsgCount;
        }

        public int getTaskMsgCount() {
            return taskMsgCount;
        }

        public void setTaskMsgCount(int taskMsgCount) {
            this.taskMsgCount = taskMsgCount;
        }
    }
}
