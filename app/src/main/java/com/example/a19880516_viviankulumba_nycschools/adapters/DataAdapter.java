package com.example.a19880516_viviankulumba_nycschools.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a19880516_viviankulumba_nycschools.R;
import com.example.a19880516_viviankulumba_nycschools.models.NYCHighSchools;
import com.example.a19880516_viviankulumba_nycschools.models.SATScores;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.AutoTransition;
import androidx.transition.TransitionManager;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    public ArrayList<NYCHighSchools> nycHighSchoolsArrayList;
    public ArrayList<SATScores> satScoresArrayList;
    private Context context;

    public DataAdapter(ArrayList<NYCHighSchools> nycHighSchoolsArrayList, ArrayList<SATScores> satScoresArrayList, Context context) {
        this.nycHighSchoolsArrayList = nycHighSchoolsArrayList;
        this.satScoresArrayList = satScoresArrayList;
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
        SATScores satScores = satScoresArrayList.get(position);

        holder.schoolName.setText(nycHighSchools.getSchool_name());
        holder.overviewParagraph.setText(nycHighSchools.getOverview_paragraph());
        holder.button.setText("LEARN MORE");

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* This code expands our card view to show additional info */
                if (holder.schoolName.getVisibility() == View.VISIBLE
                        && holder.overviewParagraph.getVisibility() == View.VISIBLE) {
                    TransitionManager.beginDelayedTransition(holder.cardView,
                            new AutoTransition());

                    holder.location.setText("Address: " + nycHighSchools.getLocation());
                    holder.phoneNumber.setText("(p): " + nycHighSchools.getPhone_number());
                    holder.faxNumber.setText("(f): " + nycHighSchools.getFax_number());
                    holder.email.setText("(e): " + nycHighSchools.getSchool_email());
                    holder.satTag.setText("AVERAGE SAT SCORES");
                    holder.satMath.setText("Math: " + satScores.getSat_math_avg_score());
                    holder.satWriting.setText("Writing: " + satScores.getSat_writing_avg_score());
                    holder.satCritical.setText("Reading: " + satScores.getSat_critical_reading_avg_score());

                    holder.location.setVisibility(View.VISIBLE);
                    holder.phoneNumber.setVisibility(View.VISIBLE);
                    holder.faxNumber.setVisibility(View.VISIBLE);
                    holder.email.setVisibility(View.VISIBLE);
                    holder.satTag.setVisibility(View.VISIBLE);
                    holder.satMath.setVisibility(View.VISIBLE);
                    holder.satWriting.setVisibility(View.VISIBLE);
                    holder.satCritical.setVisibility(View.VISIBLE);

                    holder.schoolName.setVisibility(View.GONE);
                    holder.overviewParagraph.setVisibility(View.GONE);
                    holder.button.setText("BACK");

                } else {

                    /*This expands our card view less*/

                    TransitionManager.beginDelayedTransition(holder.cardView,
                            new AutoTransition());

                    holder.location.setVisibility(View.GONE);
                    holder.phoneNumber.setVisibility(View.GONE);
                    holder.faxNumber.setVisibility(View.GONE);
                    holder.email.setVisibility(View.GONE);
                    holder.satTag.setVisibility(View.GONE);
                    holder.satMath.setVisibility(View.GONE);
                    holder.satWriting.setVisibility(View.GONE);
                    holder.satCritical.setVisibility(View.GONE);

                    holder.schoolName.setVisibility(View.VISIBLE);
                    holder.overviewParagraph.setVisibility(View.VISIBLE);

                    holder.button.setText("LEARN MORE");
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("size of the list in getItemCount(): " + nycHighSchoolsArrayList.size());
        return nycHighSchoolsArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final CardView cardView;
        private final TextView schoolName;
        private final TextView overviewParagraph;
        private final TextView satTag;
        private final TextView satCritical;
        private final TextView satMath;
        private final TextView satWriting;
        private final TextView location;
        private final TextView phoneNumber;
        private final TextView faxNumber;
        private final TextView email;
        private final Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.card_view);
            schoolName = itemView.findViewById(R.id.schoolNameTv);
            overviewParagraph = itemView.findViewById(R.id.overParagraphTv);
            satTag = itemView.findViewById(R.id.satTag);
            satCritical = itemView.findViewById(R.id.satCriticalTv);
            satMath = itemView.findViewById(R.id.satMathTv);
            satWriting = itemView.findViewById(R.id.satWritingTv);
            location = itemView.findViewById(R.id.locationTv);
            phoneNumber = itemView.findViewById(R.id.phoneNumberTv);
            faxNumber = itemView.findViewById(R.id.faxNumberTv);
            email = itemView.findViewById(R.id.schoolEmailTv);
            button = itemView.findViewById(R.id.learnMoreBtn);

        }
    }
}
