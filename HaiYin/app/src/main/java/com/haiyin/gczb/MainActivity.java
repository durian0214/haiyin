package com.haiyin.gczb;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.MenuItem;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.home.HomeFragment;
import com.haiyin.gczb.my.MyFragment;
import com.haiyin.gczb.order.OrderFragment;
import com.haiyin.gczb.sendPackage.page.SendPackageActivity;
import com.haiyin.gczb.user.page.LoginActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.view.BottomNavigationViewHelper;
import com.durian.lib.bus.RxBus;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

import com.haiyin.gczb.base.BaseActivity;
import com.haiyin.gczb.demandHall.DemandHallFragment;
import com.haiyin.gczb.home.HomeFragment;
import com.haiyin.gczb.my.MyFragment;
import com.haiyin.gczb.order.OrderFragment;
import com.haiyin.gczb.user.event.LoginOutEvent;
import com.haiyin.gczb.user.page.LoginActivity;
import com.haiyin.gczb.utils.MyUtils;
import com.haiyin.gczb.utils.UserUtils;
import com.haiyin.gczb.utils.view.BottomNavigationViewHelper;

import io.reactivex.functions.Consumer;

public class MainActivity extends BaseActivity {
    private static MainActivity instance;
    private HomeFragment homeFragment;
    private MyFragment myFragment;
    private DemandHallFragment demandHallFragment;
    private OrderFragment orderFragment;
    @BindView(R.id.navigation_main)
    BottomNavigationView navigation;

    @OnClick(R.id.imgb_main_add)
    public void add() {
        if (UserUtils.isLoginToLogin()) {
            intentJump(this, SendPackageActivity.class, null);
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

        instance = this;
        isShowTitle(false);
        RxBus.getInstance().subscribe(LoginOutEvent.class, new Consumer<LoginOutEvent>() {
            @Override
            public void accept(LoginOutEvent inventoryEvent) {
                intentJump(mContext, LoginActivity.class, null);
            }
        });
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setSelectedItemId(R.id.main_home);
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        getAddress();
    }


    private void getAddress() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            return;
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            return;
        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);

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
    }




}
