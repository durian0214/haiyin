package com.haiyin.gczb.my.entity;

import com.haiyin.gczb.base.BaseEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2019/2/25.
 */
public class SalesContractFilesEntity  extends BaseEntity{

    /**
     * data : {"frameFile":["file1Url","file2Url"],"contractFile":["file1Url","file2Url"],"clearingFile":["file1Url","file2Url"]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<String> frameFile;
        private List<String> contractFile;
        private List<String> clearingFile;

        public List<String> getFrameFile() {
            return frameFile;
        }

        public void setFrameFile(List<String> frameFile) {
            this.frameFile = frameFile;
        }

        public List<String> getContractFile() {
            return contractFile;
        }

        public void setContractFile(List<String> contractFile) {
            this.contractFile = contractFile;
        }

        public List<String> getClearingFile() {
            return clearingFile;
        }

        public void setClearingFile(List<String> clearingFile) {
            this.clearingFile = clearingFile;
        }
    }
}
