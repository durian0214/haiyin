package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class NewsListEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
}
