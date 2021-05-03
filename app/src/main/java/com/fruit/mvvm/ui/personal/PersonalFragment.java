package com.fruit.mvvm.ui.personal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.fruit.mvvm.R;
import com.fruit.mvvm.viewmodels.ScrollingViewModel;

public class PersonalFragment extends Fragment {

    private ScrollingViewModel mViewModel;

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