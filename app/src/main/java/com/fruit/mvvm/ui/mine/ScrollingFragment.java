package com.fruit.mvvm.ui.mine;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.fruit.mvvm.R;
import com.fruit.mvvm.viewmodel.HomeViewModel;
import com.fruit.mvvm.viewmodel.ScrollingViewModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.util.Objects;

public class ScrollingFragment extends Fragment {

    private ScrollingViewModel mViewModel;

    public static ScrollingFragment newInstance() {
        return new ScrollingFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_scrolling, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String Detail[] = requireActivity().getIntent().getStringArrayExtra("Detail");


        mViewModel = new ViewModelProvider(this).get(ScrollingViewModel.class);
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
}