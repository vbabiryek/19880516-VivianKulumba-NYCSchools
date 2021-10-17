package com.example.a19880516_viviankulumba_nycschools.services;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.constants.Constants.BASE_URL;

/*This class builds our Retrofit instance and completes our network service for REST calls.*/
public class RetrofitBuild {
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
