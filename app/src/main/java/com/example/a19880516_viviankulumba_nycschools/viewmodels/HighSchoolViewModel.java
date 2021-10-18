package com.example.a19880516_viviankulumba_nycschools.viewmodels;

import android.app.Application;

import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.services.DataCall;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HighSchoolViewModel extends AndroidViewModel {

    private DataCall schoolsCall;
    private LiveData<List<NYCHighSchools>> highSchoolResponseLiveData;

    /*This is our constructor initializing our data call and Live data object modelled from our data class*/
    public HighSchoolViewModel(@NonNull Application application) {
        super(application);

        schoolsCall = new DataCall();
        this.highSchoolResponseLiveData = schoolsCall.getSchools();
    }

    /* This method allows us to return the Live Data which is being observed for changes */
    public LiveData<List<NYCHighSchools>> getHighSchoolResponseLiveData(){
        System.out.println("Live data from ViewModel " + highSchoolResponseLiveData.getValue());
        return highSchoolResponseLiveData;
    }

}
