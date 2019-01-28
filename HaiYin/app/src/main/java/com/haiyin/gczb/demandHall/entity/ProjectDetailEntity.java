package com.haiyin.gczb.demandHall.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class ProjectDetailEntity extends BaseEntity {


    /**
     * data : {"projectId":"1081490503305928704","pic":"图片地址","title":"标题","summary":"简介","amount":10000,"beginDate":"2019.01.05","endDate":"2019.01.25","industryName":"行业名称","address":"详细地址","projectStatus":1,"needType":2,"invoiceFileCompany":"公司发票","invoiceFileEntity":"团队发票","hasCode":true}
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
         * projectId : 1081490503305928704
         * pic : 图片地址
         * title : 标题
         * summary : 简介
         * amount : 10000
         * beginDate : 2019.01.05
         * endDate : 2019.01.25
         * industryName : 行业名称
         * address : 详细地址
         * projectStatus : 1
         * needType : 2
         * invoiceFileCompany : 公司发票
         * invoiceFileEntity : 团队发票
         * hasCode : true
         */

        private String projectId;
        private String pic;
        private String title;
        private String summary;
        private int amount;
        private String beginDate;
        private String endDate;
        private String industryName;
        private String address;
        private int projectStatus;
        private int needType;
        private String invoiceFileCompany;
        private String invoiceFileEntity;
        private boolean hasCode;

        public String getProjectId() {
            return projectId;
        }

        public void setProjectId(String projectId) {
            this.projectId = projectId;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getBeginDate() {
            return beginDate;
        }

        public void setBeginDate(String beginDate) {
            this.beginDate = beginDate;
        }

        public String getEndDate() {
            return endDate;
        }

        public void setEndDate(String endDate) {
            this.endDate = endDate;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getProjectStatus() {
            return projectStatus;
        }

        public void setProjectStatus(int projectStatus) {
            this.projectStatus = projectStatus;
        }

        public int getNeedType() {
            return needType;
        }

        public void setNeedType(int needType) {
            this.needType = needType;
        }

        public String getInvoiceFileCompany() {
            return invoiceFileCompany;
        }

        public void setInvoiceFileCompany(String invoiceFileCompany) {
            this.invoiceFileCompany = invoiceFileCompany;
        }

        public String getInvoiceFileEntity() {
            return invoiceFileEntity;
        }

        public void setInvoiceFileEntity(String invoiceFileEntity) {
            this.invoiceFileEntity = invoiceFileEntity;
        }

        public boolean isHasCode() {
            return hasCode;
        }

        public void setHasCode(boolean hasCode) {
            this.hasCode = hasCode;
        }
    }
}
