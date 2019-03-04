package com.haiyin.gczb.utils;

import android.app.Activity;
import android.app.DownloadManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.FileProvider;

import com.durian.lib.utils.LogUtil;

import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created
 * by durian
 * 2018/1/16.
 */

public class DownloadService extends Service {
    private static final String TAG = DownloadService.class.getSimpleName();

    public static final int HANDLE_DOWNLOAD = 0x001;
    public static final String BUNDLE_KEY_DOWNLOAD_URL = "download_url";
    public static final String BUNDLE_KEY_DOWNLOAD_APK_NAME = "apk_name";
    public static final float UNBIND_SERVICE = 2.0F;

    private Activity activity;
    private DownloadBinder binder;
    private DownloadManager downloadManager;
    private DownloadChangeObserver downloadObserver;
    private BroadcastReceiver downLoadBroadcast;
    private ScheduledExecutorService scheduledExecutorService;

    //下载任务ID
    private long downloadId;
    private String downloadUrl;
    private OnProgressListener onProgressListener;

    File downloadFile;
    String apkName;
    //问价
    public Handler downLoadHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (onProgressListener != null && HANDLE_DOWNLOAD == msg.what) {
                //被除数可以为0，除数必须大于0
                if (msg.arg1 >= 0 && msg.arg2 > 0) {
                    onProgressListener.onProgress(msg.arg1 / (float) msg.arg2);
                }
            }
        }
    };

    private Runnable progressRunnable = new Runnable() {
        @Override
        public void run() {
            updateProgress();
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new DownloadBinder();
    }

    @Override
    public IBinder onBind(Intent intent) {
        downloadUrl = intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_URL);
        apkName =intent.getStringExtra(BUNDLE_KEY_DOWNLOAD_APK_NAME);
        LogUtil.i("Apk下载路径传递成功：" + downloadUrl);
        downloadApk(downloadUrl,apkName);
        return binder;
    }

    /**
     * 下载最新APK
     */
    private void downloadApk(String url,String apkName) {
        this.apkName =apkName;
        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        downloadObserver = new DownloadChangeObserver();

        registerContentObserver();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        request.setMimeType("application/vnd.android.package-archive");
        /**设置用于下载时的网络状态*/
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        /**设置通知栏是否可见*/
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        /**设置漫游状态下是否可以下载*/
        request.setAllowedOverRoaming(false);
        /**如果我们希望下载的文件可以被系统的Downloads应用扫描到并管理，
         我们需要调用Request对象的setVisibleInDownloadsUi方法，传递参数true.*/
        request.setVisibleInDownloadsUi(true);
        /**设置文件保存路径*/
        downloadFile = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), apkName+".apk");
       if(downloadFile.exists()){
           downloadFile.delete();
       }
        LogUtil.e(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).getPath()+"");
        request.setDestinationUri(Uri.fromFile(downloadFile));
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setTitle("下载新版本");
        request.setVisibleInDownloadsUi(true);   /**将下载请求放入队列， return下载任务的ID*/
        downloadId = downloadManager.enqueue(request);

        registerBroadcast();
    }

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        /**注册service 广播 1.任务完成时 2.进行中的任务被点击*/
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        intentFilter.addAction(DownloadManager.ACTION_NOTIFICATION_CLICKED);
        DownCompleteReceiver downCompleteReceiver = new DownCompleteReceiver(downloadId);
        registerReceiver(downCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));

    }

    /**
     * 注销广播
     */
    private void unregisterBroadcast() {
        if (downLoadBroadcast != null) {
            unregisterReceiver(downLoadBroadcast);
            downLoadBroadcast = null;
        }
    }

    /**
     * 注册ContentObserver
     */
    private void registerContentObserver() {
        /** observer download change **/
        if (downloadObserver != null) {
            getContentResolver().registerContentObserver(Uri.parse("content://downloads/my_downloads"), false, downloadObserver);
        }
    }

    /**
     * 注销ContentObserver
     */
    private void unregisterContentObserver() {
        if (downloadObserver != null) {
            getContentResolver().unregisterContentObserver(downloadObserver);
        }
    }

    /**
     * 关闭定时器，线程等操作
     */
    private void close() {
        if (scheduledExecutorService != null && !scheduledExecutorService.isShutdown()) {
            scheduledExecutorService.shutdown();
        }

        if (downLoadHandler != null) {
            downLoadHandler.removeCallbacksAndMessages(null);
        }
    }

    /**
     * 发送Handler消息更新进度和状态
     */
    private void updateProgress() {
        int[] bytesAndStatus = getBytesAndStatus(downloadId);
        downLoadHandler.sendMessage(downLoadHandler.obtainMessage(HANDLE_DOWNLOAD, bytesAndStatus[0], bytesAndStatus[1], bytesAndStatus[2]));
    }

    /**
     * 通过query查询下载状态，包括已下载数据大小，总大小，下载状态
     *
     * @param downloadId
     * @return
     */
    private int[] getBytesAndStatus(long downloadId) {
        int[] bytesAndStatus = new int[]{
                -1, -1, 0
        };
        DownloadManager.Query query = new DownloadManager.Query().setFilterById(downloadId);
        Cursor cursor = null;
        try {
            cursor = downloadManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                //已经下载文件大小
                bytesAndStatus[0] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR));
                //下载文件的总大小
                bytesAndStatus[1] = cursor.getInt(cursor.getColumnIndexOrThrow(DownloadManager.COLUMN_TOTAL_SIZE_BYTES));
                //下载状态
                bytesAndStatus[2] = cursor.getInt(cursor.getColumnIndex(DownloadManager.COLUMN_STATUS));
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return bytesAndStatus;
    }

    /**
     * 绑定此DownloadService的Activity实例
     *
     * @param activity
     */
    public void setTargetActivity(Activity activity) {
        this.activity = activity;
    }

    /**
     * 接受下载完成广播
     */
    public class DownCompleteReceiver extends BroadcastReceiver {
        long enqueueId;

        public DownCompleteReceiver(long enqueueId) {
            this.enqueueId = enqueueId;
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            DownloadManager dm = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
            long id = intent.getExtras().getLong(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if (enqueueId != id) {
                return;
            }

            DownloadManager.Query query = new DownloadManager.Query();
            query.setFilterById(enqueueId);

            Cursor c = dm.query(query);

            if (c != null && c.moveToFirst()) {
                int columnIndex = c.getColumnIndex(DownloadManager.COLUMN_STATUS);
                // 下载失败也会返回这个广播，所以要判断下是否真的下载成功
                if (DownloadManager.STATUS_SUCCESSFUL == c.getInt(columnIndex)) {
                    if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                        Uri uri = FileProvider.getUriForFile(context, "com.qianchao.app.youhui.fileprovider", downloadFile);
                        Intent installIntent = new Intent(Intent.ACTION_VIEW);
                        installIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        installIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        installIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                        installIntent.setDataAndType(uri, "application/vnd.android.package-archive");
                        context.startActivity(installIntent);
                    } else {
                        close();
                        // 获取下载好的 apk 路径
                        String uriString = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_FILENAME));
                        // 提示用户安装
                        installAPP(Uri.parse("file://" + uriString), context);
                    }
                }
                c.close();
            }
        }

        private void installAPP(Uri data, Context context) {
            Intent promptInstall = new Intent(Intent.ACTION_VIEW)
                    .setDataAndType(data, "application/vnd.android.package-archive");
            // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
            promptInstall.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(promptInstall);
        }

    }

    /**
     * 监听下载进度
     */
    private class DownloadChangeObserver extends ContentObserver {

        public DownloadChangeObserver() {
            super(downLoadHandler);
            scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        }

        /**
         * 当所监听的Uri发生改变时，就会回调此方法
         *
         * @param selfChange 此值意义不大, 一般情况下该回调值false
         */
        @Override
        public void onChange(boolean selfChange) {
            scheduledExecutorService.scheduleAtFixedRate(progressRunnable, 0, 100, TimeUnit.MILLISECONDS);
        }
    }

    public class DownloadBinder extends Binder {
        /**
         * 返回当前服务的实例
         *
         * @return
         */
        public DownloadService getService() {
            return DownloadService.this;
        }

    }

    public interface OnProgressListener {
        /**
         * 下载进度
         *
         * @param fraction 已下载/总大小
         */
        void onProgress(float fraction);
    }

    /**
     * 对外开发的方法
     *
     * @param onProgressListener
     */
    public void setOnProgressListener(OnProgressListener onProgressListener) {
        this.onProgressListener = onProgressListener;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
        unregisterContentObserver();
    }
}