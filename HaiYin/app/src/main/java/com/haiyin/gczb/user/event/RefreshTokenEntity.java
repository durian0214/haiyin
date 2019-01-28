package com.haiyin.gczb.user.event;

import com.haiyin.gczb.base.BaseEntity;

/**
 * Created
 * by durian
 * 2019/1/28.
 */
public class RefreshTokenEntity extends BaseEntity {

    /**
     * data : {"expires_in":432000,"token":"149e04b24b7aad4a9e2357d34a19[token，string]"}
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
         * expires_in : 432000
         * token : 149e04b24b7aad4a9e2357d34a19[token，string]
         */

        private int expires_in;
        private String token;

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
