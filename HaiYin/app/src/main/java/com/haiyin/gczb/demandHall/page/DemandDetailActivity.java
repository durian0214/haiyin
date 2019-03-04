package com.haiyin.gczb.demandHall.page;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.security.rp.RPSDK;
import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.entity.DetectInfoEntity;
import com.haiyin.gczb.demandHall.entity.GetBiztokenEntity;
import com.haiyin.gczb.demandHall.entity.ProjectDetailEntity;
import com.haiyin.gczb.demandHall.presenter.FaceIdPresenter;
import com.haiyin.gczb.demandHall.presenter.ProjectPresenter;
import com.haiyin.gczb.utils.Arith;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.dialog.GrabSingleCodeDialog;
import com.haiyin.gczb.utils.http.ApiConfig;

import butterknife.BindView;
import butterknife.OnClick;

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
    @BindView(R.id.img_demeand_detail)
    ImageView img;
    String id;

    //项目状态： 1 = 待完善,业务员可以完善信息, 2=待编辑,劳务 公司的由后台发布, 3=已发布, 可以抢单,
    // 4=待打款,发包方可以申请打款, 5=打款待审核,企业点击打款，并且上传完凭证后的状态,
    // 6=待开票, 7=申请开票中, 8=已开票完成
    private int status;


    String token;

    @BindView(R.id.btn_demand_detail_grab_single)
    Button btnSingle;
    @BindView(R.id.btn_demand_detail_grab_single_code)
    Button btnCode;

    @OnClick(R.id.btn_demand_detail_grab_single)
    public void toGrabSingle() {
        //抢单
        projectPresenter.signProjectCheck("2", id, null,mContext);

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
            tvProjectAmount.setText("项目金额：￥" +entity.getData().getAmount());
            tvIndustryType.setText("行业类型：" + entity.getData().getIndustryName());
            tvIndustryAddress.setText("项目位置：" + entity.getData().getAddress());
            final String str = entity.getData().getPic();

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    GlideUtil.loaderCornersImg(mContext, img, str);
                }
            });

            status = entity.getData().getProjectStatus();
//            if (status == 3) {
//            }
        } else if (code == ApiConfig.SIGN_PROJECT_CHECK) {
            faceIdPresenter.getBiztoken(id,mContext);
        } else if (code == ApiConfig.GET_BIZTOKEN) {
            GetBiztokenEntity entity = (GetBiztokenEntity) data;
            token = entity.getData();
            toFace(token);
        } else if (code == ApiConfig.DETECT_INFO) {
            DetectInfoEntity entity = (DetectInfoEntity) data;
            if (entity.getData().getErrorCode() == 0) {
                Bundle b = new Bundle();
                b.putString("token", token);
                b.putString("id", id);
                intentJump(this, ManuallySignedActivity.class, b);
                this.finish();
            } else {
                Bundle bundle = new Bundle();
                bundle.putString("str", entity.getEm());
                bundle.putInt("type", 0);
                intentJump(this, FaceRecognitionActivity.class, bundle);
                this.finish();
            }
        }

    }

    private void toFace(String bizToken) {
//        AuthConfig.Builder configBuilder = new AuthConfig.Builder(bizToken, R.class.getPackage().getName());
//        AuthSDKApi.startMainPage(this, configBuilder.build(), new IdentityCallback() {
//            @Override
//            public void onIdentityResult(Intent intent) {
//                faceIdPresenter.detectInfo(token,mContext);
//            }
//        });
        RPSDK.start(bizToken, this,
                new RPSDK.RPCompletedListener() {
                    @Override
                    public void onAuditResult(RPSDK.AUDIT audit, String s, String s1) {
                        if(audit == RPSDK.AUDIT.AUDIT_PASS) { //认证通过
                            faceIdPresenter.detectInfo(token,mContext);
                        }
                        else if(audit == RPSDK.AUDIT.AUDIT_FAIL) { //认证不通过
                            MyUtils.showShort("认证不通过");
                        }
                        else if(audit == RPSDK.AUDIT.AUDIT_IN_AUDIT) { //认证中，通常不会出现，只有在认证审核系统内部出现超时，未在限定时间内返回认证结果时出现。此时提示用户系统处理中，稍后查看认证结果即可。

                        }
                        else if(audit == RPSDK.AUDIT.AUDIT_NOT) { //未认证，用户取消
                            MyUtils.showShort("未认证，用户取消");
                        }
                        else if(audit == RPSDK.AUDIT.AUDIT_EXCEPTION){ //系统异常
                            MyUtils.showShort("系统异常");
                        }
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
        if (Constant.userType == 2) {
            btnCode.setVisibility(View.GONE);
            btnSingle.setVisibility(View.VISIBLE);
        } else {
            btnCode.setVisibility(View.GONE);
            btnSingle.setVisibility(View.GONE);
        }
        projectPresenter = new ProjectPresenter(this);
        faceIdPresenter = new FaceIdPresenter(this);
        id = getIntent().getBundleExtra("bundle").getString("id");
        setTitle("详情");
        projectPresenter.getProjectDetail(id,mContext);
        dialog = GrabSingleCodeDialog.getIntance();
    }

}
