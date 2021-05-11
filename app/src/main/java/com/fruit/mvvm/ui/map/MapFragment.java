package com.fruit.mvvm.ui.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.fruit.mvvm.R;

import org.jetbrains.annotations.NotNull;

public class MapFragment extends Fragment implements AMapLocationListener, LocationSource {
    public static final LatLng XIAN = new LatLng(34.263161, 108.948024);// 北京市经纬度
    protected static CameraPosition cameraPosition;
    private final AMapLocationListener aMapLocationListener = null;
    private MapView mapView;
    private AMap aMap;
    private FragmentActivity activity;
    private OnLocationChangedListener onLocationChangedListener = null;
    private AMapLocationClient aMapLocationClient = null;

    private LatLng getTarget() {
        return XIAN;
    }

    private CameraPosition getCameraPosition() {
        return cameraPosition;
    }

    private void setCameraPosition(CameraPosition cameraPosition) {
        MapFragment.cameraPosition = cameraPosition;
    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_map, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mapView = requireView().findViewById(R.id.map);
        aMap = mapView.getMap();

        uiSetting();

        if (mapView != null) {
            mapView.onCreate(savedInstanceState);
            aMap = mapView.getMap();
            if (getCameraPosition() == null) {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(getTarget(), 10, 0, 0)));
            } else {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(getCameraPosition()));
            }
        }

        init();
    }

    // UISetting
    void uiSetting() {
        UiSettings mUiSettings = aMap.getUiSettings();//定义一个UiSettings对象

        mUiSettings.setCompassEnabled(true);
        mUiSettings.setMyLocationButtonEnabled(true); //显示默认的定位按钮
        mUiSettings.setScaleControlsEnabled(true);//控制比例尺控件是否显示

        aMap.setMyLocationEnabled(true);// 可触发定位并显示当前位置


        aMap.showBuildings(true);
        aMap.showIndoorMap(true);
    }


    void init() {
        LatLng HENGSHAN = new LatLng(37.958, 109.29568);
        LatLng ZHOUZHI = new LatLng(34.17, 108.2);
        LatLng LANTIAN = new LatLng(34.15, 109.32);
        LatLng HUXIAN = new LatLng(34.1, 108.6);

        LatLng[] latLngs = {HENGSHAN, ZHOUZHI, LANTIAN, HUXIAN};
        String[] titles = {"榆林市横山县", "西安市周至县", "西安市蓝田县", "西安市户县"};

        batchMarker(latLngs, titles);
    }

    // batchMarker
    void batchMarker(LatLng[] latLngs, String[] titles) {
        for (int i = 0; i < latLngs.length; i++) {
            final Marker marker = aMap.addMarker(new MarkerOptions().position(latLngs[i]));
            marker.setTitle(titles[i]);
            marker.setSnippet("导航到这里");

            // 定义 Marker 点击事件监听
            int finalI = i;
            AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
                // marker 对象被点击时回调的接口
                // 返回 true 则表示接口已响应事件，否则返回false
                @Override
                public boolean onMarkerClick(Marker marker) {
                    markForNavi(getTarget(), "我的位置", latLngs[finalI], titles[finalI]);
                    return true;
                }
            };

            // 绑定 Marker 被点击事件
            aMap.setOnMarkerClickListener(markerClickListener);
        }
    }

    void markForNavi(LatLng lstart, String lname, LatLng rend, String rname) {
        Poi start = new Poi(lname, lstart, "start");
        //终点
        Poi end = new Poi(rname, rend, "end");
        //构建导航组件配置类，没有传入起点，所以起点默认为 “我的位置”
        AmapNaviParams params = new AmapNaviParams(start, null, end, AmapNaviType.DRIVER, AmapPageType.ROUTE);
        //启动导航组件
        AmapNaviPage.getInstance().showRouteActivity(getContext(), params, null);
    }


    // 定位监听
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
            Log.e("AmapError", "location Error, ErrCode:"
                    + aMapLocation.getErrorCode() + ", errInfo:"
                    + aMapLocation.getErrorInfo());

            Log.e("type", String.valueOf(aMapLocation.getLocationType()));
        } else {
            Log.e("AmapError", "aMapLocation null");
        }
    }

    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {
        this.onLocationChangedListener = onLocationChangedListener;

        if (aMapLocationClient != null) {
            aMapLocationClient = new AMapLocationClient(activity);
            AMapLocationClientOption aMapLocationClientOption = new AMapLocationClientOption();

            aMapLocationClient.setLocationListener(this);
            aMapLocationClientOption.setInterval(20);
            aMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
            aMapLocationClientOption.setGpsFirst(false);

            aMapLocationClient.setLocationOption(aMapLocationClientOption);
            aMapLocationClient.startLocation();

        }
    }

    @Override
    public void deactivate() {

    }

    // 生命周期
    @Override
    public void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        activity = getActivity();
    }

    // 接管生命周期

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(@NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        setCameraPosition(aMap.getCameraPosition());
        super.onDestroy();
        mapView.onDestroy();
    }

    /*
    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

     */
}