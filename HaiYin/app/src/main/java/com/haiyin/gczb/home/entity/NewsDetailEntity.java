package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class NewsDetailEntity extends BaseEntity {

    /**
     * data : {"title":"非盟委员会主席法基祝贺嫦娥四号探测器成功落月","summary":"4日当天，除了参加中国援建的非盟总部综合服务中心项目的移交仪式，非盟委员会主席法基还对中国嫦娥四号探测器成功落月表示热烈祝贺。 法基说，对于人类来说，月球遥不可及，但中国凭借超强科技能力","pic":"http://pub.oss.jiuniok.com/201901/dododoc1d0eo00j04eb1qkgao6pnv1bega/dododoc1d0eo00j04eb1qkgao6pnv1bega.jpeg","content":"4日当天，除了参加中国援建的非盟总部综合服务中心项目的移交仪式，非盟委员会主席法基还对中国嫦娥四号探测器成功落月表示热烈祝贺。 法基说，对于人类来说，月球遥不可及，但中国凭借超强科技能力","createDate":"2019.01.05"}
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
         * title : 非盟委员会主席法基祝贺嫦娥四号探测器成功落月
         * summary : 4日当天，除了参加中国援建的非盟总部综合服务中心项目的移交仪式，非盟委员会主席法基还对中国嫦娥四号探测器成功落月表示热烈祝贺。 法基说，对于人类来说，月球遥不可及，但中国凭借超强科技能力
         * pic : http://pub.oss.jiuniok.com/201901/dododoc1d0eo00j04eb1qkgao6pnv1bega/dododoc1d0eo00j04eb1qkgao6pnv1bega.jpeg
         * content : 4日当天，除了参加中国援建的非盟总部综合服务中心项目的移交仪式，非盟委员会主席法基还对中国嫦娥四号探测器成功落月表示热烈祝贺。 法基说，对于人类来说，月球遥不可及，但中国凭借超强科技能力
         * createDate : 2019.01.05
         */

        private String title;
        private String summary;
        private String pic;
        private String content;
        private String createDate;

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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreateDate() {
            return createDate;
        }

        public void setCreateDate(String createDate) {
            this.createDate = createDate;
        }
    }
}
