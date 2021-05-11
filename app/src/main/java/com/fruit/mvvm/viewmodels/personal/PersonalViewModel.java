package com.fruit.mvvm.viewmodels.personal;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fruit.mvvm.ui.MineActivity;

public class PersonalViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private MutableLiveData<String> mText;

    public PersonalViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue(getText().getValue());
    }

    public void onSettingsClick(View view)
    {

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        mText.setValue(text);
    }


}