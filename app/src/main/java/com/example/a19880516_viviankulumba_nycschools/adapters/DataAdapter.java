package com.example.a19880516_viviankulumba_nycschools.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a19880516_viviankulumba_nycschools.MainActivity;
import com.example.a19880516_viviankulumba_nycschools.R;
import com.example.a19880516_viviankulumba_nycschools.fragments.MoreInfoDialogFrag;
import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    public ArrayList<NYCHighSchools> nycHighSchoolsArrayList;
    private Context context;

    public DataAdapter(ArrayList<NYCHighSchools> nycHighSchoolsArrayList, Context context) {
        this.nycHighSchoolsArrayList = nycHighSchoolsArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.high_school_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {
        NYCHighSchools nycHighSchools = nycHighSchoolsArrayList.get(position);

        holder.schoolName.setText(nycHighSchools.getSchool_name());
        holder.overviewParagraph.setText(nycHighSchools.getOverview_paragraph());

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = ((AppCompatActivity) context).getSupportFragmentManager();
                MoreInfoDialogFrag moreInfoDialogFrag = new MoreInfoDialogFrag();
                moreInfoDialogFrag.show(fragmentManager, "MoreInfoDialogFrag");
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("size of the list in getItemCount(): " + nycHighSchoolsArrayList.size());
        return nycHighSchoolsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView schoolName;
        private final TextView overviewParagraph;
        private final Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schoolName = itemView.findViewById(R.id.schoolNameTv);
            overviewParagraph = itemView.findViewById(R.id.overParagraphTv);
            button = itemView.findViewById(R.id.learnMoreBtn);

        }
    }
}
