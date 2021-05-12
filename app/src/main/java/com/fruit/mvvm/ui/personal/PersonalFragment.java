package com.fruit.mvvm.ui.personal;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.fruit.mvvm.R;
import com.fruit.mvvm.ui.settings.SettingsActivity;
import com.fruit.mvvm.viewmodels.personal.PersonalViewModel;
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity;

public class PersonalFragment extends Fragment {

    // base data
    private static final int PERMISSION_REQUEST_LOCATION = 0;
    private static final int PERMISSION_REQUEST_STORAGE = 1;

    // hyper data
    private PersonalViewModel mViewModel;

    public static PersonalFragment newInstance() {
        return new PersonalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        String[] Detail = requireActivity().getIntent().getStringArrayExtra("Detail");

        mViewModel = new ViewModelProvider(this).get(PersonalViewModel.class);
        // Use context content
        requireView().findViewById(R.id.permission).setOnClickListener(
                v -> {
                    requestTwoPermissions();
                }
        );
        requireView().findViewById(R.id.settings).setOnClickListener(
                v -> {
                    Intent intent = new Intent(getActivity(), SettingsActivity.class);
                    startActivity(intent);
                }
        );
        requireView().findViewById(R.id.license).setOnClickListener(
                v -> {

                    OssLicensesMenuActivity.setActivityTitle("开源许可");
                    startActivity(new Intent(requireActivity(), OssLicensesMenuActivity.class));
                }
        );

        requireView().findViewById(R.id.license).setOnClickListener(
                v -> {

                    OssLicensesMenuActivity.setActivityTitle("开源许可");
                    startActivity(new Intent(requireActivity(), OssLicensesMenuActivity.class));
                }
        );

        requireView().findViewById(R.id.information).setOnClickListener(
                v -> {
                    newDialog();
                }
        );

        requireView().findViewById(R.id.address).setOnClickListener(
                v -> {
                    newDialog();
                }
        );
        requireView().findViewById(R.id.contact).setOnClickListener(
                v -> {
                    newDialog();
                }
        );
        requireView().findViewById(R.id.license).setOnClickListener(
                v -> {

                    OssLicensesMenuActivity.setActivityTitle("开源许可");
                    startActivity(new Intent(requireActivity(), OssLicensesMenuActivity.class));
                }
        );


        // TODO: Use the ViewModel
        final TextView textView = requireView().findViewById(R.id.account_name);
        textView.setText(Detail[0]);
        requireActivity().setTitle(Detail[0]);

        /*
        mViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
         */
    }

    public void newDialog() {
        new AlertDialog.Builder(requireContext()).setTitle("正在开发中！").setPositiveButton("我需要这个功能!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "会做的", Toast.LENGTH_LONG).show();
            }
        }).setNegativeButton("不需要这个功能", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(requireActivity(), "还是会做的", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }


    public void requestTwoPermissions() {
        // BEGIN_INCLUDE(startCamera)
        // Check if the Camera permission has been granted
        // Permission is missing and must be requested.
        requestLocationPermission();
        // Permission is missing and must be requested.
        requestStoragePermission();
    }

    /**
     * Requests the {@link android.Manifest.permission#CAMERA} permission.
     * If an additional rationale should be displayed, the user has to launch the request from
     * a SnackBar that includes additional information.
     */
    private void requestStoragePermission() {
        new AlertDialog.Builder(requireActivity()).setTitle("请求存储权限").setPositiveButton("我需要此权限!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(requireActivity(),
                        new String[]{
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        },
                        PERMISSION_REQUEST_STORAGE);
            }
        }).setNegativeButton("不需要存储", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(requireActivity(), "好吧", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    private void requestLocationPermission() {
        // Request the permission. The result will be received in onRequestPermissionResult().
        new AlertDialog.Builder(requireActivity()).setTitle("请求定位权限").setPositiveButton("我需要此权限!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ActivityCompat.requestPermissions(requireActivity(),
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
                Toast.makeText(requireActivity(), "好吧", Toast.LENGTH_SHORT).show();
            }
        }).show();
    }
}