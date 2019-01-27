package com.haiyin.gczb.my.fragment;

import android.os.Bundle;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;

/**
 * Created
 * by durian
 * 2019/1/26.
 */
public class PDFFragment extends BaseFragment {
    @BindView(R.id.pdfview)
    PDFView pdf;


    String strPdf;

    public static PDFFragment getInstance(String strPdf) {
        PDFFragment fragment = new PDFFragment();
        fragment.strPdf = strPdf;
        return fragment;
    }

    @Override
    protected int setView() {
        return R.layout.fragment_pdf;
    }

    @Override
    protected void init(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL uri = new URL(strPdf);//注意，这里的URL地址必须为网络地址，
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
    public void onDestroyView() {
        super.onDestroyView();
        pdf.recycle();
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }
}
