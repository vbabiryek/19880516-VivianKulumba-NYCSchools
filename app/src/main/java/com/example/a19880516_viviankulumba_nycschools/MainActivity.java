package com.example.a19880516_viviankulumba_nycschools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ProgressBar;
import com.example.a19880516_viviankulumba_nycschools.adapters.DataAdapter;
import com.example.a19880516_viviankulumba_nycschools.fragments.MoreInfoDialogFrag;
import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.constants.Constants.FRAGMENT_TAG;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter adapter;
    private HighSchoolViewModel highSchoolViewModel;
    private SATScoresViewModel satScoresViewModel;
    private ArrayList<NYCHighSchools> nycHighSchoolsArrayList = new ArrayList<>();
    private ArrayList<SATScores> satScoresArrayList = new ArrayList<>();
    private SharedPreferences sharedPref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new DataAdapter(nycHighSchoolsArrayList, this);
        highSchoolViewModel = new ViewModelProvider(this).get(HighSchoolViewModel.class);
        satScoresViewModel = new ViewModelProvider(this).get(SATScoresViewModel.class);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setHasFixedSize(true);
                recyclerView.setAdapter(adapter);
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        getSchoolsResponse();
        getScoresResponse();
    }

    private void getSchoolsResponse(){
        highSchoolViewModel.getHighSchoolResponseLiveData().observe(this, nycHighSchools -> {
            if(nycHighSchools != null){
                progressBar.setVisibility(View.GONE);
                for(int i = 0; i < nycHighSchools.size(); i++){
                    nycHighSchoolsArrayList.add(nycHighSchools.get(i));
                    Set<String> set = new HashSet<>();
                    set.add(nycHighSchoolsArrayList.get(i).getPhone_number());
                    set.add(nycHighSchoolsArrayList.get(i).getFax_number());
                    set.add(nycHighSchoolsArrayList.get(i).getSchool_email());
                    set.add(nycHighSchoolsArrayList.get(i).getLocation());

                    sharedPref = getPreferences(Context.MODE_PRIVATE);
                    editor = sharedPref.edit();
                    editor.putStringSet("sharedPrefSet", set);

                    System.out.println("set size: " + set.size());

//                    editor.putString("schoolPhoneNumber", nycHighSchoolsArrayList.get(i).getPhone_number())
//                        .putString("schoolFaxNumber", nycHighSchoolsArrayList.get(i).getFax_number())
//                        .putString("schoolEmail", nycHighSchoolsArrayList.get(i).getSchool_email())
//                        .putString("schoolLocation", nycHighSchoolsArrayList.get(i).getLocation())
//                        .apply();

                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void getScoresResponse(){
        satScoresViewModel.getSATScoresResponseLiveData().observe(this, satScores -> {
            if(satScores != null){
                for(int i = 0; i < satScores.size(); i++){
                    satScoresArrayList.add(satScores.get(i));
                    sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString("schoolName", satScoresArrayList.get(i).getSchool_name());
                    editor.putString("satCriticalReadingAvgScore", satScoresArrayList.get(i).getSat_critical_reading_avg_score());
                    editor.putString("satMathAvgScore", satScoresArrayList.get(i).getSat_math_avg_score());
                    editor.putString("satWritingAvgScore", satScoresArrayList.get(i).getSat_writing_avg_score())
                    .apply();
                    System.out.println("My list from getScoresResponse() " + satScoresArrayList.get(i));
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}