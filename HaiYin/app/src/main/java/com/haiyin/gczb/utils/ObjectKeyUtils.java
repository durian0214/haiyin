package com.haiyin.gczb.utils;

/**
 * Created
 * by durian
 * 2019/1/23.
 */
public class ObjectKeyUtils  {
    private static ObjectKeyUtils intance = null;
    public static ObjectKeyUtils getIntance() {
        synchronized (ObjectKeyUtils.class) {
            if (intance == null) {
                intance = new ObjectKeyUtils();

            }
        }
        return intance;
    }



    //头像  avatars/${unix timestamp}${rand}.jpg
    public String getAvatars() {

        return "avatars/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //发包项目图片   projects/banners/${unix timestamp}${rand}.jpg
    public String getProjects() {
        return "projects/banners/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    营业执照	haiyin-private	客户端	companys/license/${unix timestamp}${rand}.jpg
    public String getCompanysLicense() {
        return  "companys/license/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    法人身份证正面照片	haiyin-private	客户端	company/corporation/${unix timestamp}${rand}.jpg
    public String getCompanyCorporation() {
        return "company/corporation/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    收款人身份证扫描件	haiyin-private	客户端	company/payee/${unix timestamp}${rand}.jpg
    public String getCompanyPayee() {
        return "company/payee/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    框架合同图片	haiyin-private	客户端	project/frame/${userid}/${unix timestamp}${rand}.jpg
    public String getProjectFrame() {
        return "project/frame/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    订单合同图片	haiyin-private	客户端	project/contract/${userid}/${unix timestamp}${rand}.jpg
    public String getProjectContract() {
        return "project/contract/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
    //    项目结算单图片	haiyin-private	客户端	project/settlement/${userid}/${unix timestamp}${rand}.jpg
    public String getProjectSettlement() {
        return  "project/settlement/" + MyUtils.getNowTime() + MyUtils.getRandomnum() + ".jpg";
    }
}
