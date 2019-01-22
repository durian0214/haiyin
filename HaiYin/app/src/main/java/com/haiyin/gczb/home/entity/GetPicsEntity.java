package com.haiyin.gczb.home.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class GetPicsEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * picPath : http://pub.oss.jiuniok.com/201901/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba.png
         * tilte : 名称
         * urlPath : 链接地址
         */

        private String picPath;
        private String tilte;
        private String urlPath;

        public String getPicPath() {
            return picPath;
        }

        public void setPicPath(String picPath) {
            this.picPath = picPath;
        }

        public String getTilte() {
            return tilte;
        }

        public void setTilte(String tilte) {
            this.tilte = tilte;
        }

        public String getUrlPath() {
            return urlPath;
        }

        public void setUrlPath(String urlPath) {
            this.urlPath = urlPath;
        }
    }
}
