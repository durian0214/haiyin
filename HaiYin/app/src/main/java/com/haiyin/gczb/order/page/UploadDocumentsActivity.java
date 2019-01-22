package com.haiyin.gczb.order.page;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.order.presenter.OrderPresenter;

import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/14.
 */
public class UploadDocumentsActivity extends BaseActivity implements BaseView {
    OrderPresenter orderPresenter;
    private String id;
    private String url;
    @OnClick(R.id.btn_upload_documents)
    public void toUpload(){
        orderPresenter.payFileSave(id,url);
    }
    @Override
    public void success(int code, Object data) {

    }

    @Override
    public void netError(String msg) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_upload_documents;
    }

    @Override
    public void initView() {
        orderPresenter = new OrderPresenter(this);
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("上传凭证");
    }
}
