package com.haiyin.gczb.home.page;

import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2018/12/29.
 */
public class WebActivity extends BaseActivity {
    @BindView(R.id.web)
    WebView web;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    public void initView() {
        String url = getIntent().getBundleExtra("bundle").getString("url");
        String title = getIntent().getBundleExtra("bundle").getString("title");
        setTitle(title);
        setWeb(web).loadUrl(url);
    }
    /**
     * 设置webView
     *
     * @param web
     * @return
     */
    public static WebView setWeb(WebView web) {
        WebSettings ws = web.getSettings();
        // 告诉WebView启用JavaScript执行。默认的是false。
        ws.setJavaScriptEnabled(true);
        // 网页内容的宽度是否可大于WebView控件的宽度
        ws.setLoadWithOverviewMode(false);

        //  页面加载好以后，再放开图片
        ws.setBlockNetworkImage(false);
        // 使用localStorage则必须打开
        ws.setDomStorageEnabled(true);
        // 保存表单数据
        ws.setSaveFormData(true);
        ws.setAllowFileAccess(true); //设置可以访问文件
        ws.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        // 是否应该支持使用其屏幕缩放控件和手势缩放
        ws.setSupportZoom(true);
        ws.setBuiltInZoomControls(true);
        ws.setDisplayZoomControls(false);
        // 启动应用缓存
        ws.setAppCacheEnabled(true);
//        ws.setAppCachePath(Environment.getExternalStorageDirectory()+"/youhui2");
        // 设置缓存模式
        ws.setCacheMode(WebSettings.LOAD_DEFAULT);

        // setDefaultZoom  api19被弃用
        // 设置此属性，可任意比例缩放。
        ws.setUseWideViewPort(true);
        // 缩放比例 1
        web.setInitialScale(1);
        // 排版适应屏幕
        ws.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        ws.setDomStorageEnabled(true);


        ws.setDefaultTextEncodingName("UTF-8");
        ws.setAllowContentAccess(true); // 是否可访问Content Provider的资源，默认值 true
        ws.setAllowFileAccess(true);    // 是否可访问本地文件，默认值 true
        // 是否允许通过file url加载的Javascript读取本地文件，默认值 false
        ws.setAllowFileAccessFromFileURLs(false);
        // 是否允许通过file url加载的Javascript读取全部资源(包括文件,http,https)，默认值 false
        ws.setAllowUniversalAccessFromFileURLs(false);
        //webview从5.0开始默认不允许混合模式,https中不能加载http资源,需要设置开启。
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ws.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }
        return web;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
