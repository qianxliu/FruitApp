package com.fruit.mvvm.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fruit.mvvm.R;
import com.fruit.mvvm.ui.login.fragment.ForgetFragment;

public class ForgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forget_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, ForgetFragment.newInstance())
                    .commitNow();
        }
    }
}