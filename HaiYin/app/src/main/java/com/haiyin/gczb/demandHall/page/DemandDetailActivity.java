package com.haiyin.gczb.demandHall.page;

import android.content.Intent;
import android.widget.TextView;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.GetBiztokenEntity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.FaceIdPresenter;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.dialog.GrabSingleCodeDialog;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.durian.lib.base.BaseView;
import com.tencent.authsdk.AuthConfig;
import com.tencent.authsdk.AuthSDKApi;
import com.tencent.authsdk.callback.IdentityCallback;

import butterknife.BindView;
import butterknife.OnClick;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.GetBiztokenEntity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.FaceIdPresenter;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.dialog.GrabSingleCodeDialog;
import com.haiyin.gczb.utils.http.ApiConfig;

/**
 * Created
 * by durian
 * 2019/1/4.
 * 需求详情
 */
public class DemandDetailActivity extends BaseActivity implements BaseView {
    ProjectPresenter projectPresenter;
    FaceIdPresenter faceIdPresenter;
    private GrabSingleCodeDialog dialog;
    @BindView(R.id.tv_demand_detail_name)
    TextView tvName;
    @BindView(R.id.tv_demand_detail_describe)
    TextView tvDescribe;
    @BindView(R.id.tv_demand_detail_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_demand_detail_end_time)
    TextView tvEndTime;
    @BindView(R.id.tv_demand_detail_project_amount)
    TextView tvProjectAmount;
    @BindView(R.id.tv_demand_detail_industry_type)
    TextView tvIndustryType;
    @BindView(R.id.tv_demand_detail_industry_address)
    TextView tvIndustryAddress;
    String id;

    @OnClick(R.id.btn_demand_detail_grab_single)
    public void toGrabSingle() {
        //抢单
        projectPresenter.signProjectCheck("2", id, null);

    }

    @OnClick(R.id.btn_demand_detail_grab_single_code)
    public void toGrabSingleCode() {
        //编码抢单
        dialog.showGrabSingleCodeDialog(this, projectPresenter, id);
    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.PROJECT_DETAIL) {
            ProjectDetailEntity entity = (ProjectDetailEntity) data;
            tvName.setText("项目名称：" + entity.getData().getTitle());
            tvDescribe.setText("项目描述：" + entity.getData().getSummary());
            tvStartTime.setText("开工时间：" + entity.getData().getBeginDate());
            tvEndTime.setText("完工时间：" + entity.getData().getEndDate());
            tvProjectAmount.setText("项目金额：￥" + entity.getData().getAmount());
            tvIndustryType.setText("行业类型：" + entity.getData().getIndustryName());
            tvIndustryAddress.setText("项目位置：" + entity.getData().getAddress());
        } else if (code == ApiConfig.SIGN_PROJECT_CHECK) {
            faceIdPresenter.getBiztoken(id);
        } else if (code == ApiConfig.GET_BIZTOKEN) {
            GetBiztokenEntity entity = (GetBiztokenEntity) data;
            toFace(entity.getData());
        }

    }

    private void toFace(String bizToken) {
        AuthConfig.Builder configBuilder = new AuthConfig.Builder(bizToken, R.class.getPackage().getName());
        AuthSDKApi.startMainPage(this, configBuilder.build(), new IdentityCallback() {
            @Override
            public void onIdentityResult(Intent intent) {

            }
        });
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_demand_detail;
    }

    @Override
    public void initView() {
        projectPresenter = new ProjectPresenter(this);
        faceIdPresenter = new FaceIdPresenter(this);
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("详情");
        projectPresenter.getProjectDetail(id);
        dialog = GrabSingleCodeDialog.getIntance();
    }

}
