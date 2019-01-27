package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class PaymentCertificateEntity extends BaseEntity {

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * taxProofId : 1082681449276121088
         * proofFile : http://pri.oss.jiuniok.com/201901/dododoc1d0oitn1810gj1ai0hu7gh1qp2v/dododoc1d0oitn1810gj1ai0hu7gh1qp2v.png
         */

        private String taxProofId;
        private String proofFile;

        public String getTaxProofId() {
            return taxProofId;
        }

        public void setTaxProofId(String taxProofId) {
            this.taxProofId = taxProofId;
        }

        public String getProofFile() {
            return proofFile;
        }

        public void setProofFile(String proofFile) {
            this.proofFile = proofFile;
        }
    }
}
