package com.haiyin.gczb.my.page;

import android.widget.EditText;

import com.haiyin.gczb.base.BaseActivity;
import com.durian.lib.base.BaseView;

import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.presenter.CardPresenter;
import com.haiyin.gczb.utils.MyUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/9.
 */
public class AddCardActivity extends BaseActivity implements BaseView {
    CardPresenter cardPresenter;

    //纳税人识别号
    @BindView(R.id.edt_add_card_taxpayers)
    EditText edtCardTaxpayers;
    //开户行地址
    @BindView(R.id.edt_add_card_address)
    EditText edtCardName;
    //电话
    @BindView(R.id.edt_add_card_phone)
    EditText edtPhone;
    //开户行账号
    @BindView(R.id.edt_add_card_account)
    EditText edtAccount;
    //开户行名称
    @BindView(R.id.edt_add_card_bank_name)
    EditText edtBankName;

    @OnClick(R.id.btn_add_card)
    public void add() {
        String cardTaxpayers = edtCardTaxpayers.getText().toString();
        String cardName = edtCardName.getText().toString();
        String phone = edtPhone.getText().toString();
        String account = edtAccount.getText().toString();
        String bankName = edtBankName.getText().toString();


        if (cardName.isEmpty() ) {
            MyUtils.showShort("请输入开户行地址");
            return;
        } if (cardTaxpayers.isEmpty() ) {
            MyUtils.showShort("请输入纳税人识别号");
            return;
        } if (phone.isEmpty() ) {
            MyUtils.showShort("请输入电话");
            return;
        } if (account.isEmpty() ) {
            MyUtils.showShort("请输入开户行账号");
            return;
        }if (bankName.isEmpty() ) {
            MyUtils.showShort("请输入开户行名称");
            return;
        }

        cardPresenter.addBankCard(cardTaxpayers, account, cardName, phone, bankName,mContext);

    }

    @Override
    public void success(int code, Object data) {
        BaseEntity entity = (BaseEntity) data;
        MyUtils.showShort("添加成功");
        setResult(120);
        this.finish();
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_card;
    }

    @Override
    public void initView() {
        setTitle("添加银行卡");
        cardPresenter = new CardPresenter(this);

    }
}
