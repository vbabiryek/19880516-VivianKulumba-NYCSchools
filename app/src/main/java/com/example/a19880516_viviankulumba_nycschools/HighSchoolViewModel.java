package com.example.a19880516_viviankulumba_nycschools;

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

    public HighSchoolViewModel(@NonNull Application application) {
        super(application);

        schoolsCall = new DataCall();
        this.highSchoolResponseLiveData = schoolsCall.getSchools();
    }

    public LiveData<List<NYCHighSchools>> getHighSchoolResponseLiveData(){
        System.out.println("Live data from ViewModel " + highSchoolResponseLiveData.getValue());
        return highSchoolResponseLiveData;
    }

}
