package com.fruit.mvvm.viewmodels.plantbase;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fruit.mvvm.model.farmer.Farmer;

import java.util.List;

public class FarmerViewModel extends ViewModel {
    private MutableLiveData<List<Farmer>> farmers;

    public LiveData<List<Farmer>> getUsers() {
        if(farmers == null){
            farmers = new MutableLiveData<>();
            loadUsers();
        }
        return farmers;
    }

    private void loadUsers() {

    }
}
