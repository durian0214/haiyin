package com.haiyin.gczb.utils;

import com.haiyin.gczb.home.entity.GetCityEntity;

import java.util.List;

/**
 * Created
 * by durian
 * 2018/12/28.
 */
public class Constant {
    public  static  String   cityId = "1089092165489516544";
    public static String cityName ="北京";
    //1=企业用户, 2=个体用户, 3=个人用户,4=业务员
    public  static  int   userType = 0;
    public static  int screenWidth = 0;//屏幕宽度
    public static  String appVersionName ="";//当前app版本号
    public static  int appVersionCode =0;//当前app版本 code
    public static  String deviceBrand ="";//手机型号
    public static  String deviceSysversion ="";//手机版本号


    public static List<GetCityEntity.DataBean> listCity;
}
