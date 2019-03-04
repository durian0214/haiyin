package com.haiyin.gczb.user.entity;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/2/15.
 */
public class GetVersionEntity extends BaseEntity {

    /**
     * data : {"buildNumber":34,"appPackage":"http://xx.xx.com/xxx.apk[安装包下载地址，string]","configInfo":"改进了部分bug，UI优化[版本说明，string]","versionName":"3.1.1[对外版本号，string]","updateType":1}
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
         * buildNumber : 34 [内部构建号，int]
         * appPackage : http://xx.xx.com/xxx.apk[安装包下载地址，string]
         * configInfo : 改进了部分bug，UI优化[版本说明，string]
         * versionName : 3.1.1[对外版本号，string]
         * updateType : 1 [升级类型，int，1=允许升级，3=强制升级]
         */

        private int buildNumber;
        private String appPackage;
        private String configInfo;
        private String versionName;
        private int updateType;

        public int getBuildNumber() {
            return buildNumber;
        }

        public void setBuildNumber(int buildNumber) {
            this.buildNumber = buildNumber;
        }

        public String getAppPackage() {
            return appPackage;
        }

        public void setAppPackage(String appPackage) {
            this.appPackage = appPackage;
        }

        public String getConfigInfo() {
            return configInfo;
        }

        public void setConfigInfo(String configInfo) {
            this.configInfo = configInfo;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getUpdateType() {
            return updateType;
        }

        public void setUpdateType(int updateType) {
            this.updateType = updateType;
        }
    }
}
