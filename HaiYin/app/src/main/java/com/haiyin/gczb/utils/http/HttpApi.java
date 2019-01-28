package com.haiyin.gczb.utils.http;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created
 * by durian
 * 2018/12/26.
 */
public interface HttpApi {
    //获取验证码
    @FormUrlEncoded
    @POST("app/common/phonekey")
    Observable<ResponseBody> sendCode(@Field("data") String data);
    //登录
    @FormUrlEncoded
    @POST("app/common/login")
    Observable<ResponseBody> doLogin(@Field("data") String data);
    //刷新token
    @FormUrlEncoded
    @POST("app/common/refresh_token")
    Observable<ResponseBody> refreshToken(@Field("data") String data);
    //消息未读数
    @FormUrlEncoded
    @POST("app/common/unreadMsgCount")
    Observable<ResponseBody> unreadMsgCount(@Field("data") String data);
    //消息列表
    @FormUrlEncoded
    @POST("app/common/msgList")
    Observable<ResponseBody> getMsgLists(@Field("data") String data);
    //公共配置图片
    @FormUrlEncoded
    @POST("app/base/pics")
    Observable<ResponseBody> basePics(@Field("data") String data);
    //新闻列表
    @FormUrlEncoded
    @POST("app/base/newsList")
    Observable<ResponseBody> getNewsList(@Field("data") String data);
    //新闻详情
    @FormUrlEncoded
    @POST("app/base/newsDetail")
    Observable<ResponseBody> getNewsDetail(@Field("data") String data);
    //项目列表
    @FormUrlEncoded
    @POST("app/project/list")
    Observable<ResponseBody> getProjectList(@Field("data") String data);
    //项目详情
    @FormUrlEncoded
    @POST("app/project/detail")
    Observable<ResponseBody> getProjectDetail(@Field("data") String data);
    //首页搜索
    @FormUrlEncoded
    @POST("app/base/search")
    Observable<ResponseBody> toSearch(@Field("data") String data);
    //企业订单列表
    @FormUrlEncoded
    @POST("app/company/projectList")
    Observable<ResponseBody> getOrder(@Field("data") String data);
    //企业项目开票申请
    @FormUrlEncoded
    @POST("app/company/applyInvoice")
    Observable<ResponseBody> applyInvoice (@Field("data") String data);
    //企业项目打款凭证保存
    @FormUrlEncoded
    @POST("app/company/payFileSave")
    Observable<ResponseBody> payFileSave (@Field("data") String data);
    //企业交易密码设置
    @FormUrlEncoded
    @POST("app/company/setPayPwd")
    Observable<ResponseBody> setPayPwd (@Field("data") String data);
    //企业交易密码修改
    @FormUrlEncoded
    @POST("app/company/modifyPayPwd")
    Observable<ResponseBody> modifyPayPwd (@Field("data") String data);
    //交易密码设置状态获取
    @FormUrlEncoded
    @POST("app/company/setPayPwdStatus")
    Observable<ResponseBody> getPayPwdStatus (@Field("data") String data);
    //注册
    @FormUrlEncoded
    @POST("app/company/regist")
    Observable<ResponseBody> regist (@Field("data") String data);
    //城市列表
    @FormUrlEncoded
    @POST("app/base/citys")
    Observable<ResponseBody> getCity (@Field("data") String data);
    //获取 biztoken
    @FormUrlEncoded
    @POST("app/common/faceid/biztoken")
    Observable<ResponseBody> getBiztoken (@Field("data") String data);
    //获取人脸核身结果
    @FormUrlEncoded
    @POST("app/common/faceid/detectInfo")
    Observable<ResponseBody> detectInfo (@Field("data") String data);
    //项目抢单校验
    @FormUrlEncoded
    @POST("app/company/signProjectCheck")
    Observable<ResponseBody> signProjectCheck (@Field("data") String data);
    //用户资料详情
    @FormUrlEncoded
    @POST("app/company/detailInfo")
    Observable<ResponseBody> getDetailInfo (@Field("data") String data);
    //业务员个人资料详情
    @FormUrlEncoded
    @POST("/app/sales/detailInfo")
    Observable<ResponseBody> salesDetailInfo (@Field("data") String data);
    //企业合同列表
    @FormUrlEncoded
    @POST("app/company/myContractCompanys")
    Observable<ResponseBody> getMyContractCompanys (@Field("data") String data);
    //企业我的合同
    @FormUrlEncoded
    @POST("app/company/myContractProjects")
    Observable<ResponseBody> myContractProjects (@Field("data") String data);
    //企业协议列表
    @FormUrlEncoded
    @POST("app/company/contractFiles")
    Observable<ResponseBody> contractFiles (@Field("data") String data);
    //企业合同列表
    @FormUrlEncoded
    @POST("app/company/projectCooperate")
    Observable<ResponseBody> projectCooperate (@Field("data") String data);
    //企业项目结算
    @FormUrlEncoded
    @POST("app/company/projectClearing")
    Observable<ResponseBody> projectClearing (@Field("data") String data);
    //企业完税证明文件列表
    @FormUrlEncoded
    @POST("app/company/taxProofs")
    Observable<ResponseBody> taxProofs (@Field("data") String data);
    //企业服务商列表
    @FormUrlEncoded
    @POST("app/company/companyList")
    Observable<ResponseBody> companyList (@Field("data") String data);
    //企业开票列表
    @FormUrlEncoded
    @POST("app/company/invoiceCompanys")
    Observable<ResponseBody> invoiceCompanys (@Field("data") String data);
    //企业开票项目列表
    @FormUrlEncoded
    @POST("app/company/invoiceCompanyProjects")
    Observable<ResponseBody> invoiceCompanyProjects (@Field("data") String data);
    //个体户项目列表
    @FormUrlEncoded
    @POST("app/company/entityProjects")
    Observable<ResponseBody> entityProjects (@Field("data") String data);
    //个体户收款数据
    @FormUrlEncoded
    @POST("app/company/entityReceivedPays")
    Observable<ResponseBody> entityReceivedPays (@Field("data") String data);
    //业务员个人资料修改
    @FormUrlEncoded
    @POST("app/sales/modifyInfo")
    Observable<ResponseBody> salesModifyInfo (@Field("data") String data);
    //业务员列表
    @FormUrlEncoded
    @POST("app/base/sales")
    Observable<ResponseBody> sales (@Field("data") String data);
    //行业列表
    @FormUrlEncoded
    @POST("app/base/industry")
    Observable<ResponseBody> industry (@Field("data") String data);
    //用户资料修改
    @FormUrlEncoded
    @POST("app/company/modifyInfo")
    Observable<ResponseBody> modifyInfo (@Field("data") String data);

    //项目发布
    @FormUrlEncoded
    @POST("app/company/publishProject")
    Observable<ResponseBody> publishProject (@Field("data") String data);
    //企业本部账号列表
    @FormUrlEncoded
    @POST("app/company/accountList")
    Observable<ResponseBody> accountList (@Field("data") String data);
    //企业本部账号添加
    @FormUrlEncoded
    @POST("app/company/addAccount")
    Observable<ResponseBody> addAccount (@Field("data") String data);
    //企业下级企业列表
    @FormUrlEncoded
    @POST("app/company/subCompanys")
    Observable<ResponseBody> subCompanys (@Field("data") String data);
    //企业下级企业添加
    @FormUrlEncoded
    @POST("app/company/addSubCompany")
    Observable<ResponseBody> addSubCompany (@Field("data") String data);
    //企业下级企业添加
    @FormUrlEncoded
    @POST("app/company/entityContracts")
    Observable<ResponseBody> entityContracts (@Field("data") String data);
    //业务员客户列表
    @FormUrlEncoded
    @POST("app/sales/companyList")
    Observable<ResponseBody> salescompanyList (@Field("data") String data);
    //业务员客户信息详情
    @FormUrlEncoded
    @POST("app/sales/companyDetail")
    Observable<ResponseBody> salescompanyDetail (@Field("data") String data);
    //业务员客户信息完善
    @FormUrlEncoded
    @POST("app/sales/editCompany")
    Observable<ResponseBody> saleseditCompany (@Field("data") String data);
    //业务员客户项目列表
    @FormUrlEncoded
    @POST("app/sales/companyProjects")
    Observable<ResponseBody> salescompanyProjects (@Field("data") String data);
    //业务员协议上传
    @FormUrlEncoded
    @POST("app/sales/completeProject")
    Observable<ResponseBody> salescompleteProject (@Field("data") String data);

    //业务员客户发包金额
    @FormUrlEncoded
    @POST("app/sales/projectAmount")
    Observable<ResponseBody> salesProjectAmount (@Field("data") String data);
    //项目抢单
    @FormUrlEncoded
    @POST("app/company/signProject")
    Observable<ResponseBody> signProject (@Field("data") String data);
    //企业银行卡列表
    @FormUrlEncoded
    @POST("app/company/bankCards")
    Observable<ResponseBody> bankCards (@Field("data") String data);
    //企业银行卡添加
    @FormUrlEncoded
    @POST("app/company/addBankCard")
    Observable<ResponseBody> addBankCard (@Field("data") String data);
    //个体户线上支付记录
    @FormUrlEncoded
    @POST("app/company/entityOnlinePays")
    Observable<ResponseBody> entityOnlinePays (@Field("data") String data);
    //个体户线下支付记录
    @FormUrlEncoded
    @POST("app/company/entityOfflinePays")
    Observable<ResponseBody> entityOfflinePays (@Field("data") String data);


}
