package com.haiyin.gczb.demandHall.page;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.utils.MyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class PDFActivity extends BaseActivity {
    @BindView(R.id.pdfview)
    PDFView pdf;

    @OnClick(R.id.btn_pdf)
    public void toNext() {
        if (lists.size() == 0) {
            Bundle bundle = new Bundle();
            bundle.putString("str", "抢单成功");
            bundle.putInt("type", 0);
            intentJump(this, FaceRecognitionActivity.class, bundle);
            this.finish();
        } else {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("lists", (ArrayList<String>) lists);
            intentJump(this, PDFActivity.class, bundle);
            this.finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pdf;
    }

    List<String> lists;


    @Override
    public void initView() {
        setTitle("电子合同");
        lists = getIntent().getBundleExtra("bundle").getStringArrayList("lists");
        final String url = lists.get(0);
        lists.remove(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL uri = new URL(url);//注意，这里的URL地址必须为网络地址，
                    URLConnection ucon = null;
                    try {
                        ucon = uri.openConnection();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    InputStream is = null;
                    try {

                        is = ucon.getInputStream();
                        pdf.fromStream(is)
                                .enableSwipe(true) // allows to block changing pages using swipe
                                .swipeHorizontal(false)
                                .enableDoubletap(true)
                                .defaultPage(0)
                                .enableAnnotationRendering(false) // render annotations (such as comments, colors or forms)
                                .password(null)
                                .scrollHandle(null)
                                .enableAntialiasing(true) // improve rendering a little bit on low-res screens
                                // spacing between pages in dp. To define spacing color, set view background
                                .spacing(0)
                                .load();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        pdf.recycle();
    }
}
