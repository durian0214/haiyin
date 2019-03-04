package com.haiyin.gczb;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.widget.Toast;

import com.durian.lib.base.BaseView;
import com.durian.lib.bus.RxBus;
import com.durian.lib.utils.LogUtil;
import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.DemandHallFragment;
import com.haiyin.gczb.home.HomeFragment;
import com.haiyin.gczb.home.entity.GetCityEntity;
import com.haiyin.gczb.home.presenter.CityPresenter;
import com.haiyin.gczb.my.MyFragment;
import com.haiyin.gczb.order.OrderFragment;
import com.haiyin.gczb.sendPackage.page.SendPackageActivity;
import com.haiyin.gczb.user.entity.GetVersionEntity;
import com.haiyin.gczb.user.event.LoginOutEvent;
import com.haiyin.gczb.user.event.RefreshTokenEntity;
import com.haiyin.gczb.user.event.UpdataTokenEvent;
import com.haiyin.gczb.user.page.LoginActivity;
import com.haiyin.gczb.user.presenter.GetVersionPresenter;
import com.haiyin.gczb.user.presenter.LoginPresenter;
import com.haiyin.gczb.utils.Constant;
import com.haiyin.gczb.utils.DownloadService;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.SharedPreferencesUtils;
import com.haiyin.gczb.utils.UserUtils;
import com.haiyin.gczb.utils.http.ApiConfig;
import com.haiyin.gczb.utils.var.SharedPreferencesVar;
import com.haiyin.gczb.utils.view.BottomNavigationViewHelper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity implements BaseView {
    private LoginPresenter loginPresenter;

    private static MainActivity instance;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private DemandHallFragment demandHallFragment;
    private OrderFragment orderFragment;
    @BindView(R.id.navigation_main)
    BottomNavigationView navigation;
    private CityPresenter cityPresenter;

    @OnClick(R.id.imgb_main_add)
    public void add() {
        if (UserUtils.isLoginToLogin()) {
            if (Constant.userType == 1) {
                intentJump(this, SendPackageActivity.class, null);
            } else {
                MyUtils.showShort("您没有发包权限");
            }
        }
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if (item.getItemId() == R.id.main_my || item.getItemId() == R.id.main_order) {
                if (!UserUtils.isLoginToLogin()) {
                    return false;
                }
            }
            if (item.getItemId() == R.id.main_order) {
                if (Constant.userType == 3 || Constant.userType == 4) {
                    MyUtils.showShort("您没有权限查看订单");
                    return false;
                }
            }
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();//开启一个Fragment事务
            hideFragments(transaction);
            if (item.getItemId() == R.id.main_home) {
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    transaction.add(R.id.fl_main_container, homeFragment);
                } else {
                    transaction.show(homeFragment);
                }
            } else if (item.getItemId() == R.id.main_demand_hall) {
                if (demandHallFragment == null) {
                    demandHallFragment = new DemandHallFragment();
                    transaction.add(R.id.fl_main_container, demandHallFragment);
                } else {
                    transaction.show(demandHallFragment);
                }
            } else if (item.getItemId() == R.id.main_order) {
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.fl_main_container, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
            } else if (item.getItemId() == R.id.main_my) {
                if (myFragment == null) {
                    myFragment = new MyFragment();
                    transaction.add(R.id.fl_main_container, myFragment);
                } else {
                    transaction.show(myFragment);
                }
            }
            transaction.commit();//一定要记得提交事务
            return true;
        }
    };
    /**
     * 显示之前隐藏所有fragment
     *
     * @param transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (homeFragment != null)//不为空才隐藏,如果不判断第一次会有空指针异常
            transaction.hide(homeFragment);
        if (myFragment != null)
            transaction.hide(myFragment);
        if (demandHallFragment != null)
            transaction.hide(demandHallFragment);
        if (orderFragment != null)
            transaction.hide(orderFragment);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        loginPresenter = new LoginPresenter(this);
        cityPresenter = new CityPresenter(this);
        instance = this;
        isShowTitle(false);
        RxBus.getInstance().toObservable(this, LoginOutEvent.class).subscribe(new Consumer<LoginOutEvent>() {
            @Override
            public void accept(LoginOutEvent msgEvent) throws Exception {
                //处理事件
                intentJump(mContext, LoginActivity.class, null);
            }
        });
        RxBus.getInstance().toObservable(this, UpdataTokenEvent.class).subscribe(new Consumer<UpdataTokenEvent>() {
            @Override
            public void accept(UpdataTokenEvent event) throws Exception {
                //处理事件
                loginPresenter.refreshToken();
            }
        });


        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.main_home);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                String url =  UploadHelper.getInstance().getPriUrl(mContext, "12");
//
//            }
//        }).start();
        getAddress();
    }


    private void getAddress() {

        cityPresenter.getCity();
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
//            return;
//        }
//        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
//            return;
//        }
////        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

    }

    private LocationManager locationManager;

    private StringBuilder stringBuilder = new StringBuilder();
    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            stringBuilder.append(getTime()).append("\t定位信息：\n");
            stringBuilder.append("\t").append(location.getLatitude()).append("\n");
            stringBuilder.append("\t").append(location.getLongitude()).append("\n");
            stringBuilder.append("\t").append(location.getProvider()).append("\n");
            String s = stringBuilder.toString();
            Geocoder gc = new Geocoder(mContext, Locale.getDefault());
            List<Address> locationList = null;
            try {
                locationList = gc.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Address address = locationList.get(0);//得到Address实例

            if (address == null) {
                return;
            }

            stringBuilder.append(getTime()).append("\t地址信息：\n");

            String countryName = address.getCountryName();//得到国家名称
            if (!TextUtils.isEmpty(countryName)) {
                stringBuilder.append(countryName);
            }

            String adminArea = address.getAdminArea();//省
            if (!TextUtils.isEmpty(adminArea)) {
                stringBuilder.append(adminArea);
            }

            String locality = address.getLocality();//得到城市名称
            if (!TextUtils.isEmpty(locality)) {
                stringBuilder.append(locality);
            }

            for (int i = 0; address.getAddressLine(i) != null; i++) {
                String addressLine = address.getAddressLine(i);
                if (!TextUtils.isEmpty(addressLine)) {
                    if (addressLine.contains(countryName)) {
                        addressLine = addressLine.replace(countryName, "");
                    }
                    if (addressLine.contains(adminArea)) {
                        addressLine = addressLine.replace(adminArea, "");
                    }
                    if (addressLine.contains(locality)) {
                        addressLine = addressLine.replace(locality, "");
                    }
                    if (!TextUtils.isEmpty(addressLine)) {
                        stringBuilder.append(addressLine);
                    }
                }
            }

            String featureName = address.getFeatureName();//得到周边信息
            if (!TextUtils.isEmpty(featureName)) {
                stringBuilder.append(featureName).append("\n");
            }

            String ss = stringBuilder.toString();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private String getTime() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault());
        return simpleDateFormat.format(new Date(System.currentTimeMillis()));
    }

    public static MainActivity getInstance() {
        return instance;
    }

    /**
     * 跳需求大厅
     */
    public void selDemand() {
        navigation.setSelectedItemId(R.id.main_demand_hall);
    }

    /**
     * 跳首页
     */
    public void selMainhome() {
        navigation.setSelectedItemId(R.id.main_home);
        HomeFragment.getInstance().isShowRed();
    }

    @Override
    public void success(int code, Object data) {
        if (code == ApiConfig.GET_CITY) {
            GetCityEntity entity = (GetCityEntity) data;
            Constant.listCity = entity.getData();
            for (int i = 0; i < Constant.listCity.size(); i++) {
                if (entity.getData().get(i).getName().contains(Constant.cityName)) {
                    Constant.cityId = entity.getData().get(i).getCityId();
                    return;
                }
            }
        } else if (code == ApiConfig.REFRESH_TOKEN) {
            RefreshTokenEntity entity = (RefreshTokenEntity) data;
            SharedPreferencesUtils.put(this, SharedPreferencesVar.TOKEN, entity.getData().getToken());
        }
    }

    @Override
    public void netError(String msg) {
        MyUtils.showShort(msg);
    }

    /*******************************
     * 定义一个变量，来标识是否退出
     */
    private static boolean isExit = false;
    /**
     * 退出延时
     */
    static Handler mHandlers = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!isExit) {
                isExit = true;
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
                // 利用handler延迟发送更改状态信息
                mHandlers.sendEmptyMessageDelayed(0, 2000);
                return true;
            } else {
                /*不退出，最小化都后台，使用这个： moveTaskToBack(false);
                 * finish(); System.exit(0);
                 */
                System.exit(0);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private boolean isBindService;


    public void bindService(String apkUrl, String apkName) {
        Intent intent = new Intent(this, DownloadService.class);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_URL, apkUrl);
        intent.putExtra(DownloadService.BUNDLE_KEY_DOWNLOAD_APK_NAME, apkName);
        isBindService = bindService(intent, conn, BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            DownloadService.DownloadBinder binder = (DownloadService.DownloadBinder) service;
            DownloadService downloadService = binder.getService();

            //接口回调，下载进度
            downloadService.setOnProgressListener(new DownloadService.OnProgressListener() {
                @Override
                public void onProgress(float fraction) {
                    LogUtil.i("下载进度：" + (int) (fraction * 100));
                    //判断是否真的下载完成进行安装了，以及是否注册绑定过服务
                    if (fraction == DownloadService.UNBIND_SERVICE && isBindService) {
                        unbindService(conn);
                        isBindService = false;
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}
