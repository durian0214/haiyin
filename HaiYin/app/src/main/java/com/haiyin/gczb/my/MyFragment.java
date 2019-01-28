package com.haiyin.gczb.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseFragment;
import com.haiyin.gczb.home.page.MessageActivity;
import com.haiyin.gczb.my.entity.UserEntity;
import com.haiyin.gczb.my.page.AddAccountListsActivity;
import com.haiyin.gczb.my.page.AddEnterpriseListsActivity;
import com.haiyin.gczb.my.page.CustomerManagementActivity;
import com.haiyin.gczb.my.page.IndividualMyContractProjectActivity;
import com.haiyin.gczb.my.page.MyContractActivity;
import com.haiyin.gczb.my.page.MyPagerActivity;
import com.haiyin.gczb.my.page.MyWalletActivity;
import com.haiyin.gczb.my.page.ProgressQueryActivity;
import com.haiyin.gczb.my.page.SalesmanSetActivity;
import com.haiyin.gczb.my.page.ServiceInformationActivity;
import com.haiyin.gczb.my.page.SetActivity;
import com.haiyin.gczb.my.presenter.UserPresenter;
import com.haiyin.gczb.user.event.SalesDetailInfoEntity;
import com.haiyin.gczb.user.page.ChangeEnterpriseInformationActivity;
import com.haiyin.gczb.user.page.EnterpriseInformationActivity;
import com.haiyin.gczb.user.page.SalesmanInformationActivity;
import com.haiyin.gczb.user.presenter.SalesPresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UserUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * Created
 * by durian
 * 2018/12/19.
 */
public class MyFragment extends BaseFragment implements BaseView {
    UserPresenter userPresenter;
    SalesPresenter salesPresenter;
    @BindView(R.id.tv_my_name)
    TextView tvName;
    @BindView(R.id.tv_my_title)
    TextView tvTitle;
    @BindView(R.id.tv_my_position)
    TextView tvPosition;
    @BindView(R.id.img_my_icon)
    RoundedImageView imgIcon;


    @BindView(R.id.ll_my_message)
    LinearLayout llMessage;
    @BindView(R.id.ll_my_wallet)
    LinearLayout llWallet;
    @BindView(R.id.ll_my_progress_query)
    LinearLayout llProgressQuery;
    @BindView(R.id.ll_my_customer_management)
    LinearLayout llCustomerManagement;
    @BindView(R.id.ll_my_contract)
    LinearLayout llContract;
    @BindView(R.id.ll_my_perfect_information)
    LinearLayout llPerfectInformation;
    @BindView(R.id.ll_my_pager)
    LinearLayout llPager;
    @BindView(R.id.ll_my_email)
    LinearLayout llEmail;
    @BindView(R.id.ll_my_add_enterprise)
    LinearLayout llAddEnterprise;
    @BindView(R.id.ll_my_add_account)
    LinearLayout llAddAccount;
    @BindView(R.id.ll_my_setting)
    LinearLayout llSetting;


    @OnClick(R.id.btn_my_loginout)
    public void toLoginOut() {
        UserUtils.outLogin();
    }
    @OnClick(R.id.tv_my_version)
    public void about() {
        MyUtils.showShort(getString(R.string.version));
    }


    @OnClick(R.id.imgb_my_set)
    public void toSet() {
        if (Constant.userType == 1) {
            Intent mIntent = new Intent(getActivity(), ChangeEnterpriseInformationActivity.class);
            startActivity(mIntent);
        } else if (Constant.userType == 2) {
            Intent mIntent = new Intent(getActivity(), ChangeEnterpriseInformationActivity.class);
            startActivity(mIntent);
        } else if (Constant.userType == 3) {

        } else if (Constant.userType == 4) {
            Intent mIntent = new Intent(getActivity(), SalesmanInformationActivity.class);
            startActivity(mIntent);

        }

    }

    @OnClick(R.id.tv_my_setting)
    public void toSetting() {
        if (Constant.userType == 3) {
            return;
        }
        //设置
        if (Constant.userType == 4) {
            Intent mIntent = new Intent(getActivity(), SalesmanSetActivity.class);
            startActivity(mIntent);

        } else {
            Intent mIntent = new Intent(getActivity(), SetActivity.class);
            startActivity(mIntent);
        }
    }

    @OnClick(R.id.tv_my_message)
    public void toMessage() {
        if (Constant.userType == 3) {
            return;
        }
        //消息
        Intent mIntent = new Intent(getActivity(), MessageActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_email)
    public void toEmail() {
        if (Constant.userType == 3) {
            return;
        }
        //服务信息
        Intent mIntent = new Intent(getActivity(), ServiceInformationActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_contract)
    public void toContract() {
        if (Constant.userType == 3) {
            return;
        }
        //合同
        if (Constant.userType == 1) {
            //企业
            Intent mIntent = new Intent(getActivity(), MyContractActivity.class);
            startActivity(mIntent);
        } else if (Constant.userType == 2) {
            //个体户
            Intent mIntent = new Intent(getActivity(), IndividualMyContractProjectActivity.class);
            startActivity(mIntent);
        }

    }

    @OnClick(R.id.tv_my_wallet)
    public void toWallet() {
        if (Constant.userType == 3) {
            return;
        }
        //钱包
        Intent mIntent = new Intent(getActivity(), MyWalletActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_pager)
    public void toPager() {
        if (Constant.userType == 3) {
            return;
        }
        //票据
        Intent mIntent = new Intent(getActivity(), MyPagerActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_progress_query)
    public void toProgressQuery() {
        if (Constant.userType == 3) {
            return;
        }
        //进度查询
        Intent mIntent = new Intent(getActivity(), ProgressQueryActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_customer_management)
    public void toCostomerManagement() {
        if (Constant.userType == 3) {
            return;
        }
        //客户管理
        Intent mIntent = new Intent(getActivity(), CustomerManagementActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_perfect_information)
    public void toPerfectInformation() {
        if (Constant.userType == 3) {
            return;
        }
        //完善个人资料
        Intent mIntent = new Intent(getActivity(), SalesmanInformationActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_add_enterprise)
    public void toAddEnterprise() {
        if (Constant.userType == 3) {
            return;
        }
        //添加下级企业
        Intent mIntent = new Intent(getActivity(), AddEnterpriseListsActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_add_account)
    public void toAddAccount() {
        if (Constant.userType == 3) {
            return;
        }
        //添加本部账号
        Intent mIntent = new Intent(getActivity(), AddAccountListsActivity.class);
        startActivity(mIntent);
    }


    @Override
    protected int setView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init(View view) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.GET_DETAIL_INFO) {
            UserEntity entity = (UserEntity) data;
            tvName.setText("负责人姓名：" + entity.getData().getContactsName());
            //1=法人, 2=负责人, 3=财务, 4=人事
            if (entity.getData().getMemberPosition() == 1) {
                tvPosition.setText("法人");
            } else if (entity.getData().getMemberPosition() == 2) {
                tvPosition.setText("负责人");
            } else if (entity.getData().getMemberPosition() == 3) {
                tvPosition.setText("财务");
            } else if (entity.getData().getMemberPosition() == 4) {
                tvPosition.setText("人事");
            }
            tvTitle.setText(entity.getData().getCompanyName());
            GlideUtil.loaderCornersImg(getActivity(), imgIcon, entity.getData().getHeadImg());
        } else if (code == ApiConfig.SALES_DETAIL_INFO) {
            SalesDetailInfoEntity entity = (SalesDetailInfoEntity) data;
            tvName.setText("负责人姓名：" + entity.getData().getName());
            //业务员职位：1=总监, 2=经理, 3=主管
            if (entity.getData().getSalesPosition() == 1) {
                tvPosition.setText("总监");
            } else if (entity.getData().getSalesPosition() == 2) {
                tvPosition.setText("经理");
            } else if (entity.getData().getSalesPosition() == 3) {
                tvPosition.setText("主管");
            }
            tvTitle.setText(entity.getData().getMobile());
            GlideUtil.loaderCornersImg(getActivity(), imgIcon, entity.getData().getHeadImg());
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            showUI();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        showUI();
    }


    private void showUI() {
        if (UserUtils.isLogin()) {

            if (Constant.userType == 4) {
                salesPresenter = new SalesPresenter(this);
                salesPresenter.salesDetailInfo();
            } else {
                userPresenter = new UserPresenter(this);
                userPresenter.getDetailInfo();
            }
            if (Constant.userType == 1) {
                //企业用户
                llEmail.setVisibility(View.VISIBLE);
                llContract.setVisibility(View.VISIBLE);
                llWallet.setVisibility(View.GONE);
                llProgressQuery.setVisibility(View.VISIBLE);
                llPager.setVisibility(View.VISIBLE);
                llCustomerManagement.setVisibility(View.GONE);
                llPerfectInformation.setVisibility(View.GONE);
                llAddEnterprise.setVisibility(View.VISIBLE);
                llAddAccount.setVisibility(View.VISIBLE);
                llSetting.setVisibility(View.VISIBLE);

            } else if (Constant.userType == 2) {
                //个体用户
                llEmail.setVisibility(View.GONE);
                llContract.setVisibility(View.VISIBLE);
                llWallet.setVisibility(View.VISIBLE);
                llProgressQuery.setVisibility(View.GONE);
                llPager.setVisibility(View.VISIBLE);
                llCustomerManagement.setVisibility(View.GONE);
                llPerfectInformation.setVisibility(View.GONE);
                llAddEnterprise.setVisibility(View.GONE);
                llAddAccount.setVisibility(View.GONE);
                llSetting.setVisibility(View.VISIBLE);
            } else if (Constant.userType == 3) {
                //个人用户
                llEmail.setVisibility(View.VISIBLE);
                llContract.setVisibility(View.VISIBLE);
                llWallet.setVisibility(View.VISIBLE);
                llProgressQuery.setVisibility(View.GONE);
                llPager.setVisibility(View.VISIBLE);
                llCustomerManagement.setVisibility(View.GONE);
                llPerfectInformation.setVisibility(View.GONE);
                llAddEnterprise.setVisibility(View.GONE);
                llAddAccount.setVisibility(View.GONE);
                llSetting.setVisibility(View.VISIBLE);
            } else if (Constant.userType == 4) {
                //业务员
                llEmail.setVisibility(View.GONE);
                llContract.setVisibility(View.GONE);
                llWallet.setVisibility(View.GONE);
                llProgressQuery.setVisibility(View.GONE);
                llPager.setVisibility(View.GONE);
                llCustomerManagement.setVisibility(View.VISIBLE);
                llPerfectInformation.setVisibility(View.VISIBLE);
                llAddEnterprise.setVisibility(View.GONE);
                llAddAccount.setVisibility(View.GONE);
                llSetting.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
