package com.example.a19880516_viviankulumba_nycschools;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import com.example.a19880516_viviankulumba_nycschools.adapters.DataAdapter;
import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private LinearLayoutManager linearLayoutManager;
    private DataAdapter adapter;
    private HighSchoolViewModel highSchoolViewModel;
    private SATScoresViewModel satScoresViewModel;
    private ArrayList<NYCHighSchools> nycHighSchoolsArrayList = new ArrayList<>();
    private ArrayList<SATScores> satScoresArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);
        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new DataAdapter(nycHighSchoolsArrayList, satScoresArrayList, this);
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
                    adapter.notifyDataSetChanged();
                    System.out.println("math score here is: " + satScoresArrayList.get(i).getSat_math_avg_score());
                    System.out.println("writing score here is: " + satScoresArrayList.get(i).getSat_writing_avg_score());
                }
            }
        });
    }

}