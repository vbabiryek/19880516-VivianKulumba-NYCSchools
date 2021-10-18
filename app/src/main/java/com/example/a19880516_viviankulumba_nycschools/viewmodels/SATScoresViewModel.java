package com.example.a19880516_viviankulumba_nycschools.viewmodels;

import android.app.Application;

import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;
import com.example.a19880516_viviankulumba_nycschools.services.DataCall;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SATScoresViewModel extends AndroidViewModel {

    private DataCall scoresCall;
    private LiveData<List<SATScores>> satScoresResponseLiveData;

    /*This is our constructor initializing our data call and Live data object modelled from our data class*/
    public SATScoresViewModel(@NonNull Application application) {
        super(application);

        scoresCall = new DataCall();
        this.satScoresResponseLiveData = scoresCall.getScores();
    }
    /* This method allows us to return the Live Data which is being observed for changes */
    public LiveData<List<SATScores>> getSATScoresResponseLiveData(){
        System.out.println("Live data from ViewModel " + satScoresResponseLiveData.getValue());
        return satScoresResponseLiveData;
    }
}
