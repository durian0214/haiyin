package com.haiyin.gczb.my.page;

import android.view.View;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.OnClick;

import com.durian.lib.base.BaseView;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.my.entity.GetPayPwdStatusEntity;
import com.haiyin.gczb.my.presenter.PayPasswordPresenter;

/**
 * Created
 * by durian
 * 2019/1/7.
 */
public class SetActivity extends BaseActivity implements BaseView{
    PayPasswordPresenter payPasswordPresenter;
    @OnClick(R.id.rl_set_add_card)
    public void toAddCard(){
        intentJump(this,AddCardListsActivity.class,null);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_set;
    }
    @OnClick(R.id.rl_set_pay_password)
    public void toSet(){
        intentJump(this,SetPayPasswordActivity.class,null);
    }
    @OnClick(R.id.rl_set_change_pay_password)
    public void toChange(){
        intentJump(this,ChangePayPasswordActivity.class,null);
    }


    @BindView(R.id.rl_set_pay_password)
    RelativeLayout rlSet;
    @BindView(R.id.rl_set_change_pay_password)
    RelativeLayout rlChange;
    @Override
    public void initView() {
        payPasswordPresenter = new PayPasswordPresenter(this);
        payPasswordPresenter.getPayPwdStatus();
        setTitle("设置");
    }

    @Override
    public void success(int code, Object data) {
        GetPayPwdStatusEntity entity  = (GetPayPwdStatusEntity) data;
        if(entity.getData()==1){
            rlChange.setVisibility(View.VISIBLE);
            rlSet.setVisibility(View.GONE);
        }else {
            rlChange.setVisibility(View.GONE);
            rlSet.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void netError(String msg) {

    }
}
