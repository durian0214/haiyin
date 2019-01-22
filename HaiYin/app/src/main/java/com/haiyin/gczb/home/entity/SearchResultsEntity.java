package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class SearchResultsEntity extends BaseEntity {

    /**
     * data : {"newsList":[{"newsId":"1081490503305928704","createDate":"2019.01.04","title":"非盟委员会主席法基祝贺嫦娥四号探测器成功落月","summary":"4日当天，除了参加中国援建的非盟总部综，对于人类来说，月球遥不可及，但中国凭借超强科技能力","pic":"http://pub.oss.jiuniok.com/201901/dododoc1d0eo00j04eb1qkgao6pnv1bega/dododoc1d0eo00j04eb1qkgao6pnv1bega.jpeg"}],"projectList":[{"projectId":"1081490503305928704","title":"标题","cityName":"城市","amount":10000,"summary":"简介","days":6,"address":"详细地址"}]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NewsListBean> newsList;
        private List<ProjectListBean> projectList;

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public List<ProjectListBean> getProjectList() {
            return projectList;
        }

        public void setProjectList(List<ProjectListBean> projectList) {
            this.projectList = projectList;
        }

        public static class NewsListBean {
            /**
             * newsId : 1081490503305928704
             * createDate : 2019.01.04
             * title : 非盟委员会主席法基祝贺嫦娥四号探测器成功落月
             * summary : 4日当天，除了参加中国援建的非盟总部综，对于人类来说，月球遥不可及，但中国凭借超强科技能力
             * pic : http://pub.oss.jiuniok.com/201901/dododoc1d0eo00j04eb1qkgao6pnv1bega/dododoc1d0eo00j04eb1qkgao6pnv1bega.jpeg
             */

            private String newsId;
            private String createDate;
            private String title;
            private String summary;
            private String pic;

            public String getNewsId() {
                return newsId;
            }

            public void setNewsId(String newsId) {
                this.newsId = newsId;
            }

            public String getCreateDate() {
                return createDate;
            }

            public void setCreateDate(String createDate) {
                this.createDate = createDate;
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

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }
        }

        public static class ProjectListBean {
            /**
             * projectId : 1081490503305928704
             * title : 标题
             * cityName : 城市
             * amount : 10000
             * summary : 简介
             * days : 6
             * address : 详细地址
             */

            private String projectId;
            private String title;
            private String cityName;
            private int amount;
            private String summary;
            private int days;
            private String address;

            public String getProjectId() {
                return projectId;
            }

            public void setProjectId(String projectId) {
                this.projectId = projectId;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getCityName() {
                return cityName;
            }

            public void setCityName(String cityName) {
                this.cityName = cityName;
            }

            public int getAmount() {
                return amount;
            }

            public void setAmount(int amount) {
                this.amount = amount;
            }

            public String getSummary() {
                return summary;
            }

            public void setSummary(String summary) {
                this.summary = summary;
            }

            public int getDays() {
                return days;
            }

            public void setDays(int days) {
                this.days = days;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }
    }
}
