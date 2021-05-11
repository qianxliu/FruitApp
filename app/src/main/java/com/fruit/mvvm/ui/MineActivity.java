package com.fruit.mvvm.ui;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.fruit.mvvm.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MineActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_LOCATION = 0;
    private static final int PERMISSION_REQUEST_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

        String[] idName = getIntent().getStringArrayExtra("Detail");
        Log.d("Detail", idName[0]);

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        /*
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_plantbase, R.id.navigation_map, R.id.navigation_personal)
                .build();
         */
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        requestTwoPermissions();
    }


    // Permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_LOCATION) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Toast.makeText(this, "定位权限已获得！", Toast.LENGTH_SHORT).show();
            } else {
                // Permission request was denied.
                requestTwoPermissions();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }

    public void requestTwoPermissions() {
        // BEGIN_INCLUDE(startCamera)
        // Check if the Camera permission has been granted

        // Todo
        // ACCESS_BACKGROUND_LOCATION 无法被请求
        String[] permissions = {
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                //Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.CHANGE_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
        };



        for (String permission : permissions)
        {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is missing and must be requested.
                requestLocationPermission();
            }
        }

        String[] storagePermissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE
        };
        for (String permission : storagePermissions)
        {
            if (ActivityCompat.checkSelfPermission(this, permission)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is missing and must be requested.
                requestStoragePermission();
            }
        }
    }

    /**
     * Requests the {@link android.Manifest.permission#CAMERA} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestStoragePermission() {
        new AlertDialog.Builder(this).setTitle("请求存储权限").setPositiveButton("我需要此权限!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MineActivity.this,
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        PERMISSION_REQUEST_STORAGE);
            }
        }).setNegativeButton("不需要存储", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MineActivity.this, "好吧", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    private void requestLocationPermission() {
        // Request the permission. The result will be received in onRequestPermissionResult().
        new AlertDialog.Builder(this).setTitle("请求定位权限").setPositiveButton("我需要此权限!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(MineActivity.this,
                        new String[]{
                                Manifest.permission.ACCESS_COARSE_LOCATION,
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                                Manifest.permission.ACCESS_LOCATION_EXTRA_COMMANDS,
                                Manifest.permission.ACCESS_WIFI_STATE,
                                Manifest.permission.CHANGE_WIFI_STATE,
                                Manifest.permission.READ_PHONE_STATE,
                                Manifest.permission.ACCESS_NETWORK_STATE,

                                Manifest.permission.FOREGROUND_SERVICE,
                                Manifest.permission.ACCESS_BACKGROUND_LOCATION,
                        },
                        PERMISSION_REQUEST_LOCATION);
            }
        }).setNegativeButton("不需要定位", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MineActivity.this, "好吧", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}