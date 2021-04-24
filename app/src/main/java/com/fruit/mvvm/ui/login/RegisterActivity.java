package com.fruit.mvvm.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.fruit.mvvm.R;
import com.fruit.mvvm.ui.login.fragment.RegisterFragment;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, RegisterFragment.newInstance())
                    .commitNow();
        }
    }
}