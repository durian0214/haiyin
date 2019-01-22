package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class MessageListsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * msgId : 1082681449276121088
         * title : 消息标题
         * content : 消息内容...
         * createDate : 2019.01.09
         * readDate : null
         */

        private String msgId;
        private String title;
        private String content;
        private String createDate;
        private Object readDate;

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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

        public Object getReadDate() {
            return readDate;
        }

        public void setReadDate(Object readDate) {
            this.readDate = readDate;
        }
    }
}
