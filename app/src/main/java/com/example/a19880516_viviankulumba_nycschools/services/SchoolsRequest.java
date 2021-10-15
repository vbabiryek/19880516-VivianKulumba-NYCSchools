package com.example.a19880516_viviankulumba_nycschools.services;

import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SchoolsRequest {

    @GET("/resource/s3k6-pzi2.json")
    Call<List<NYCHighSchools>> getSchools();

    @GET("/resource/f9bf-2cp4.json")
    Call<List<SATScores>> getScores();

}
