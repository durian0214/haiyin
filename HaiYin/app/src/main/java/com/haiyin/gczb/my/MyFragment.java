package com.haiyin.gczb.my;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
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
import com.haiyin.gczb.my.page.ProgressQueryActivity;
import com.haiyin.gczb.my.page.SalesmanSetActivity;
import com.haiyin.gczb.my.page.ServiceInformationActivity;
import com.haiyin.gczb.my.page.SetActivity;
import com.haiyin.gczb.my.presenter.UserPresenter;
import com.haiyin.gczb.user.page.ChangeEnterpriseInformationActivity;
import com.haiyin.gczb.user.page.EnterpriseInformationActivity;
import com.haiyin.gczb.user.page.SalesmanInformationActivity;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UserUtils;
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
    @BindView(R.id.tv_my_name)
    TextView tvName;
    @BindView(R.id.tv_my_title)
    TextView tvTitle;
    @BindView(R.id.tv_my_position)
    TextView tvPosition;
    @BindView(R.id.img_my_icon)
    RoundedImageView imgIcon;

    @OnClick(R.id.btn_my_loginout)
    public void toLoginOut() {
        UserUtils.outLogin();
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
        //消息
        Intent mIntent = new Intent(getActivity(), MessageActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_email)
    public void toEmail() {
        //服务信息
        Intent mIntent = new Intent(getActivity(), ServiceInformationActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_contract)
    public void toContract() {
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
        //钱包
//        Intent mIntent = new Intent(getActivity(), MyBusinessActivity.class);
//        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_pager)
    public void toPager() {
        //票据
        Intent mIntent = new Intent(getActivity(), MyPagerActivity.class);
        startActivity(mIntent);
    }

    @OnClick(R.id.tv_my_progress_query)
    public void toProgressQuery() {
        //进度查询
        Intent mIntent = new Intent(getActivity(), ProgressQueryActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_customer_management)
    public void toCostomerManagement() {
        //客户管理
        Intent mIntent = new Intent(getActivity(), CustomerManagementActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_perfect_information)
    public void toPerfectInformation() {
        //完善个人资料
        Intent mIntent = new Intent(getActivity(), SalesmanInformationActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_add_enterprise)
    public void toAddEnterprise() {
        //添加下级企业
        Intent mIntent = new Intent(getActivity(), AddEnterpriseListsActivity.class);
        startActivity(mIntent);

    }

    @OnClick(R.id.tv_my_add_account)
    public void toAddAccount() {
        //添加本部账号
        Intent mIntent = new Intent(getActivity(), AddAccountListsActivity.class);
        startActivity(mIntent);
    }


    //服务信息
    @BindView(R.id.tv_my_email)
    TextView tvEmail;
    //合同
    @BindView(R.id.tv_my_contract)
    TextView tvContract;
    //钱包
    @BindView(R.id.tv_my_wallet)
    TextView tvWallet;
    //票据
    @BindView(R.id.tv_my_pager)
    TextView tvPager;
    //进度查询
    @BindView(R.id.tv_my_progress_query)
    TextView tvProgressQuery;
    //客户管理
    @BindView(R.id.tv_my_customer_management)
    TextView tvCustomerManagement;
    //完善个人资料
    @BindView(R.id.tv_my_perfect_information)
    TextView tvPerfectInformation;
    //添加下级企业
    @BindView(R.id.tv_my_add_enterprise)
    TextView tvAddEnterprise;
    //添加本部账号
    @BindView(R.id.tv_my_add_account)
    TextView tvAddAccount;


    @Override
    protected int setView() {
        return R.layout.fragment_my;
    }

    @Override
    protected void init(View view) {


//        myPresenter = new MyPresenter(this);

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        userPresenter = new UserPresenter(this);
        userPresenter.getDetailInfo();
    }

    @Override
    public void success(int code, Object data) {
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
    }


    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        super.onFragmentVisibleChange(isVisible);
        if(isVisible){
            if (Constant.userType == 1) {
                //企业用户
                tvEmail.setVisibility(View.VISIBLE);
                tvContract.setVisibility(View.VISIBLE);
                tvWallet.setVisibility(View.GONE);
                tvProgressQuery.setVisibility(View.VISIBLE);
                tvPager.setVisibility(View.VISIBLE);
                tvCustomerManagement.setVisibility(View.GONE);
                tvPerfectInformation.setVisibility(View.GONE);
                tvAddEnterprise.setVisibility(View.VISIBLE);
                tvAddAccount.setVisibility(View.VISIBLE);

            } else if (Constant.userType == 2) {
                //个体用户
                tvEmail.setVisibility(View.GONE);
                tvContract.setVisibility(View.VISIBLE);
                tvWallet.setVisibility(View.VISIBLE);
                tvProgressQuery.setVisibility(View.GONE);
                tvPager.setVisibility(View.VISIBLE);
                tvCustomerManagement.setVisibility(View.GONE);
                tvPerfectInformation.setVisibility(View.GONE);
                tvAddEnterprise.setVisibility(View.GONE);
                tvAddAccount.setVisibility(View.GONE);
            } else if (Constant.userType == 3) {
                //个人用户
                tvEmail.setVisibility(View.VISIBLE);
                tvContract.setVisibility(View.VISIBLE);
                tvWallet.setVisibility(View.VISIBLE);
                tvProgressQuery.setVisibility(View.GONE);
                tvPager.setVisibility(View.VISIBLE);
                tvCustomerManagement.setVisibility(View.GONE);
                tvPerfectInformation.setVisibility(View.GONE);
                tvAddEnterprise.setVisibility(View.GONE);
                tvAddAccount.setVisibility(View.GONE);
            } else if (Constant.userType == 4) {
                //业务员
                tvEmail.setVisibility(View.GONE);
                tvContract.setVisibility(View.GONE);
                tvWallet.setVisibility(View.GONE);
                tvProgressQuery.setVisibility(View.GONE);
                tvPager.setVisibility(View.GONE);
                tvCustomerManagement.setVisibility(View.VISIBLE);
                tvPerfectInformation.setVisibility(View.VISIBLE);
                tvAddEnterprise.setVisibility(View.GONE);
                tvAddAccount.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }
}
