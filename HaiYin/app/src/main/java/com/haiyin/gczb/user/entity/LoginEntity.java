package com.haiyin.gczb.user.entity;

import com.haiyin.gczb.base.BaseEntity;


/**
 * Created
 * by durian
 * 2019/1/12.
 */
public class LoginEntity extends BaseEntity {

    /**
     * data : {"name":"小明[名称，string]","mobile":"18310692518[联系电话，string]","headImg":"http://pub.oss.jiuniok.com/201901/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba.png[头像，string]","memberPosition":"负责人[职位，string]","roleType":"业务员 [角色，string]","expires_in":"864000[token有效时间， int， 秒]","token":"09e1737eed0c61f52d35c30af1139af3[token,string]"}
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
         * name : 小明[名称，string]
         * mobile : 18310692518[联系电话，string]
         * headImg : http://pub.oss.jiuniok.com/201901/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba/dododoc1d0bdj9dn1v1a17ru178gl7t1f8ba.png[头像，string]
         * memberPosition : 负责人[职位，string]
         * roleType : 业务员 [角色，string]
         * expires_in : 864000[token有效时间， int， 秒]
         * token : 09e1737eed0c61f52d35c30af1139af3[token,string]
         */

        private String name;
        private String mobile;
        private String headImg;
        private int memberPosition;
        private int roleType;
        private String expires_in;
        private String token;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }

        public int getMemberPosition() {
            return memberPosition;
        }

        public void setMemberPosition(int memberPosition) {
            this.memberPosition = memberPosition;
        }

        public int getRoleType() {
            return roleType;
        }

        public void setRoleType(int roleType) {
            this.roleType = roleType;
        }

        public String getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(String expires_in) {
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
