package com.example.a19880516_viviankulumba_nycschools.services;

import android.util.Log;

import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCall {
    private static final String TAG = DataCall.class.getSimpleName();
    private SchoolsRequest request;

    public DataCall(){
        request = RetrofitBuild.getRetrofitInstance().create(SchoolsRequest.class);
    }


    public LiveData<List<NYCHighSchools>> getSchools(){
        final MutableLiveData<List<NYCHighSchools>> schoolsData = new MutableLiveData<>();
        request.getSchools().enqueue(new Callback<List<NYCHighSchools>>() {
            @Override
            public void onResponse(Call<List<NYCHighSchools>> call, Response<List<NYCHighSchools>> response) {
                Log.d(TAG, "onResponse response = " + response);
                if(response.body() != null) {
                    schoolsData.setValue(response.body());
                    Log.d(TAG, "response.body().get(0) from getSchools() " + response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<NYCHighSchools>> call, Throwable t) {
                schoolsData.setValue(null);
                Log.d(TAG, "Failed");
            }
        });
        return schoolsData;
    }

    public LiveData<List<SATScores>> getScores(){
        final MutableLiveData<List<SATScores>> scoresData = new MutableLiveData<>();
        request.getScores().enqueue(new Callback<List<SATScores>>() {
            @Override
            public void onResponse(Call<List<SATScores>> call, Response<List<SATScores>> response) {
                Log.d(TAG, "onResponse response = " + response);
                if(response.body() != null){
                    scoresData.setValue(response.body());
                    Log.d(TAG, "response.body().get(0) from getScores() " + response.body().get(0));
                }
            }

            @Override
            public void onFailure(Call<List<SATScores>> call, Throwable t) {
                scoresData.setValue(null);
                Log.d(TAG, "Failed");
            }
        });
        return scoresData;
    }

}
