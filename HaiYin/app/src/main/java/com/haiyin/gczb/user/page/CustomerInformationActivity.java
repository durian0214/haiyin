package com.haiyin.gczb.user.page;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.durian.lib.base.BaseView;
import com.durian.lib.glide.GlideUtil;
import com.durian.lib.utils.LogUtil;
import com.haiyin.gczb.R;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.base.BaseEntity;
import com.haiyin.gczb.my.entity.SalesCompanyDetailEntity;
import com.haiyin.gczb.my.presenter.CustomerPresenter;
import com.haiyin.gczb.user.entity.IndustryEntity;
import com.haiyin.gczb.user.entity.SalesEntity;
import com.haiyin.gczb.user.presenter.GetDataPresenter;
import com.haiyin.gczb.utils.ImageDisposeUtil;
import com.haiyin.gczb.utils.MyPermissions;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.ObjectKeyUtils;
import com.haiyin.gczb.utils.UploadHelper;
import com.haiyin.gczb.utils.dialog.PopupUtil;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.pic.CropActivity;
import com.haiyin.gczb.utils.pic.OnPictureSelectedListener;
import com.kevin.crop.UCrop;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created
 * by durian
 * 2019/1/4.
 * 客户信息
 */
public class CustomerInformationActivity extends BaseActivity implements BaseView {
    GetDataPresenter getDataPresenter;
    CustomerPresenter customerPresenter;

    //头像
    @BindView(R.id.img_improve_customer_information_icon)
    RoundedImageView imgIcon;

    //公司名称
    @BindView(R.id.tv_customer_information_name)
    TextView tvName;
    //公司联系方式
    @BindView(R.id.tv_customer_information_contact)
    TextView tvContact;
    //联系人姓名
    @BindView(R.id.tv_customer_information_contact_name)
    TextView tvContactName;
    //联系人电话
    @BindView(R.id.tv_customer_information_contact_phone)
    TextView tvContactPhone;
    //职位
    @BindView(R.id.tv_customer_information_position)
    TextView tvPosition;
    //行业
    @BindView(R.id.tv_customer_information_industry)
    TextView tvIndustry;
    //收款人姓名
    @BindView(R.id.tv_customer_information_collection_name)
    TextView tvCollectionName;
    //银行卡号
    @BindView(R.id.tv_customer_information_bank_code)
    TextView tvBankCode;
    //收款人身份证号
    @BindView(R.id.tv_customer_information_collection_codeid)
    TextView tvCollectionCodeid;
    //银行名称
    @BindView(R.id.tv_customer_information_bank_name)
    TextView tvBankName;
    //营业执照
    @BindView(R.id.img_customer_information_business_license)
    RoundedImageView imgBusinessLicense;
    //上传证件
    @BindView(R.id.img_customer_information_upload_documents_positive)
    RoundedImageView imgUploadDocumentsPositive;
    @BindView(R.id.img_customer_information_upload_documents_back)
    RoundedImageView imgUploadDocumentsBacl;
    //收款人身份证扫描件
    @BindView(R.id.img_customer_information_collection_upload_documents_positive)
    RoundedImageView imgCollectionUploadDocumentsPositive;
    @BindView(R.id.img_customer_information_collection_upload_documents_back)
    RoundedImageView imgCollectionUploadDocumentsBck;


    private List<IndustryEntity.DataBean> industryList;
    String id;



    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.INDUSTRY) {
            IndustryEntity entity = (IndustryEntity) data;
            industryList = entity.getData();

        } else if(code ==ApiConfig.SALES_COMPANY_DETAIL){
            SalesCompanyDetailEntity entity = (SalesCompanyDetailEntity) data;
            GlideUtil.loaderCornersImg(this,imgIcon,entity.getData().getHeadImg());
            tvName.setText(entity.getData().getCompanyName());
            tvContact.setText(entity.getData().getCompanyPhone());
            tvContactName.setText(entity.getData().getContactsName());
            tvContactPhone.setText(entity.getData().getContactsPhone());
            tvBankCode.setText(entity.getData().getCardNo());
            tvCollectionCodeid.setText(entity.getData().getIdCardNo());
            tvCollectionName.setText(entity.getData().getFinaName());
            tvBankName.setText(entity.getData().getBankName());
            tvPosition.setText(getResources().getStringArray(R.array.member)[entity.getData().getMemberPosition()]);
            for (int i = 0 ; i<industryList.size();i++){
               if( industryList.get(i).getIndustryId().equals(entity.getData().getCompanyId())){
                   tvIndustry.setText(industryList.get(i).getName());
                   return ;
                }
            }
            if(!entity.getData().getBusinessLicensePic().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgBusinessLicense,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getBusinessLicensePic()));
            }
            if(!entity.getData().getCorpCardFront().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgUploadDocumentsPositive,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getCorpCardFront()));
            }
            if(!entity.getData().getCorpCardBack().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgUploadDocumentsBacl,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getCorpCardBack()));
            }
            if(!entity.getData().getFinaCardFront().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgCollectionUploadDocumentsPositive,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getFinaCardFront()));
            }
            if(!entity.getData().getFinaCardBack().isEmpty()){
                GlideUtil.loaderCornersImg(this,imgCollectionUploadDocumentsBck,UploadHelper.getInstance().getPriUrl(mContext,entity.getData().getFinaCardBack()));
            }
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_information;
    }

    @Override
    public void initView() {
        getDataPresenter = new GetDataPresenter(this);
        customerPresenter = new CustomerPresenter(this);
        getDataPresenter.industry();
            Bundle b = getIntent().getBundleExtra("bundle");
            id = b.getString("id");
            setTitle("客户信息");
            customerPresenter.salescompanyDetail(id);

    }

}
