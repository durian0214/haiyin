package com.haiyin.gczb.utils;


import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSAuthCredentialsProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.haiyin.gczb.base.BaseApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created
 * by durian
 * 2019/1/15.
 */
public class UploadHelper {
    private static OSS oss;
    private static OSS osspri;

//    法人身份证正面照片	haiyin-private	客户端	company/corporation/${unix timestamp}${rand}.jpg
//    法人身份证反面照片	haiyin-private	客户端	company/corporation/${unix timestamp}${rand}.jpg
//    收款人身份证扫描件	haiyin-private	客户端	company/payee/${unix timestamp}${rand}.jpg
//    收款人身份证扫描件	haiyin-private	客户端	company/payee/${unix timestamp}${rand}.jpg
//    销售身份证正面照片	haiyin-private	客户端	sales/${unix timestamp}${rand}.jpg
//    销售身份证反面照片	haiyin-private	客户端	sales/${unix timestamp}${rand}.jpg
//    付款凭证文件	haiyin-private	客户端	paypic/${projectId}/${userid}${rand}.jpg
//    人脸核身结果文件	haiyin-private	客户端	detectinfo/${userid}/${unix timestamp}${rand}.txt
//    人脸核身视频文件	haiyin-private	客户端	detectinfo/${userid}/${unix timestamp}${rand}.mp4
//    手写签名图片	haiyin-private	客户端	signname/${userid}/${unix timestamp}${rand}.png


    private static UploadHelper instance;
    private static final String pri_host = "http://pri.oss.jiuniok.com/";
    private static final String pub_host = "http://pub.oss.jiuniok.com/";

    private static final String P_ENDPOINT = "http://oss-cn-beijing.aliyuncs.com";//主机地址（OSS文档中有提到）

    private static final String PUB_STSSERVER = "https://app.jiuniok.com/app/common/oss_auth/haiyin-public";
    private static final String PRI_STSSERVER = "https://app.jiuniok.com/app/common/oss_auth/haiyin-private";

    private static final String PUB_BUCKETNAME = "haiyin-public";
    private static final String PRI_BUCKETNAME = "haiyin-private";


    private SimpleDateFormat simpleDateFormat;

    public UploadHelper() {

    }

    public static UploadHelper getInstance() {

        if (instance == null) {

            if (instance == null) {

                return new UploadHelper();

            }

        }

        return instance;

    }

    /**
     * 初始化公共
     *
     * @param context
     */
    private void getOssPub(Context context) {
//推荐使用OSSAuthCredentialsProvider。token过期可以及时更新
        OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(PUB_STSSERVER);
//该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();

        conf.setConnectionTimeout(15 * 1000);// 连接超时，默认15秒

        conf.setSocketTimeout(15 * 1000);// socket超时，默认15秒

        conf.setMaxConcurrentRequest(5);// 最大并发请求数，默认5个

        conf.setMaxErrorRetry(2);// 失败后最大重试次数，默认2次

        oss = new OSSClient(context, P_ENDPOINT, credentialProvider);

        if (simpleDateFormat == null) {

            simpleDateFormat = new SimpleDateFormat("yyyyMM");

        }

    }

    /**
     * 初始化私有
     *
     * @param context
     */
    private void getOssPri(Context context) {
//推荐使用OSSAuthCredentialsProvider。token过期可以及时更新
        OSSCredentialProvider credentialProvider = new OSSAuthCredentialsProvider(PRI_STSSERVER);
//该配置类如果不设置，会有默认配置，具体可看该类
        ClientConfiguration conf = new ClientConfiguration();

        conf.setConnectionTimeout(15 * 1000);// 连接超时，默认15秒

        conf.setSocketTimeout(15 * 1000);// socket超时，默认15秒

        conf.setMaxConcurrentRequest(5);// 最大并发请求数，默认5个

        conf.setMaxErrorRetry(2);// 失败后最大重试次数，默认2次

        osspri = new OSSClient(context, P_ENDPOINT, credentialProvider);

        if (simpleDateFormat == null) {

            simpleDateFormat = new SimpleDateFormat("yyyyMM");

        }

    }

    /**
     * 上传图片 上传文件
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param img_name      上传到oss后的文件名称，图片要记得带后缀 如：.jpg
     * @param imgPath       图片的本地路径
     */

    public void upImage(Context context, final UploadHelper.OssUpCallback ossUpCallback, final String img_name, String imgPath) {
        getOssPub(context);
        final Date data = new Date();
        data.setTime(System.currentTimeMillis());
        PutObjectRequest putObjectRequest = new PutObjectRequest(PUB_BUCKETNAME, simpleDateFormat.format(data) + "/" + img_name, imgPath);
        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);
            }

        });

        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                Log.e("MyOSSUtils", "------getRequestId:" + result.getRequestId());
// try {

                try {
                    ossUpCallback.successImg(oss.presignConstrainedObjectURL(PUB_BUCKETNAME, simpleDateFormat.format(data) + "/" + img_name, 30 * 60));
                } catch (ClientException e) {
                    e.printStackTrace();
                }

// } catch (ClientException e) {

// e.printStackTrace();

// }
            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                ossUpCallback.successImg(null);
            }
        });

    }

    /**
     * * 上传图片 上传流 公共图片
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param imgName       上传到oss后的文件名称，图片要记得带后缀 如：.jpg
     * @param imgbyte       图片的byte数组
     */

    public void upImagePub(Context context, final UploadHelper.OssUpCallback ossUpCallback, final String imgName, byte[] imgbyte) {

        getOssPub(context);

        final Date data = new Date();

        data.setTime(System.currentTimeMillis());

        PutObjectRequest putObjectRequest = new PutObjectRequest(PUB_BUCKETNAME, imgName, imgbyte);

        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);

            }


        });

        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                Log.e("MyOSSUtils", "------getRequestId:" + result.getRequestId());

                ossUpCallback.successImg(pub_host + "/" + imgName);

            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                }
            }

        });

    }

    /**
     * 上传图片 上传流 私有图片
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param imgName       上传到oss后的文件名称，图片要记得带后缀 如：.jpg
     * @param imgbyte       图片的byte数组
     */

    public void upImagePri(Context context, final UploadHelper.OssUpCallback ossUpCallback, final String imgName, byte[] imgbyte) {
        //上传到oss后的文件名称，图片要记得带后缀 如：.jpg

        getOssPri(context);

        final Date data = new Date();

        data.setTime(System.currentTimeMillis());

        PutObjectRequest putObjectRequest = new PutObjectRequest(PRI_BUCKETNAME, imgName, imgbyte);

        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object request, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);

            }


        });

        osspri.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest request, OSSResult result) {
                Log.e("MyOSSUtils", "------getRequestId:" + result.getRequestId());
                ossUpCallback.successImg(pri_host + imgName);


            }

            @Override
            public void onFailure(OSSRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {
                    // 本地异常如网络异常等
                    clientException.printStackTrace();
                }
                if (serviceException != null) {
                    // 服务异常
                }

            }

        });

    }

    /**
     * 私有图片转换
     *
     * @param objectKey
     * @return
     */
    public String getPriUrl(Context mContext, final String objectKey) {
        String url = "";
        if(osspri==null){
            getOssPri(mContext);
        }
        if(objectKey!=null){
            try {
                String s = objectKey.replace("http://pri.oss.jiuniok.com/", "");
                url = osspri.presignConstrainedObjectURL(PRI_BUCKETNAME, s, 30 * 60);
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }




        return url;
    }

    public interface OssUpCallback {

        void successImg(String img_url);

        void successVideo(String video_url);

        void inProgress(long progress, long zong);

    }

}
