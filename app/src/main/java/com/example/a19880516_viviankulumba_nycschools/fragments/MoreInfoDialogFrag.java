package com.example.a19880516_viviankulumba_nycschools.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.a19880516_viviankulumba_nycschools.R;

public class MoreInfoDialogFrag extends DialogFragment {

    /*
    * This DialogFrag makes a REST GET call for additional data.
    * */
    public CardView cardView;

    public TextView location;
    public TextView phoneNumber;
    public TextView faxNumber;
    public TextView schoolEmail;
    public TextView satTag;
    public TextView satCriticalReadingAvgScore;
    public TextView satMathAvgScore;
    public TextView satWritingAvgScore;

    public String schoolPhoneNumber;
    public String schoolFaxNumber;
    public String schoolEmailStr;
    public String schoolLocation;
    public String satCritical;
    public String satMath;
    public String satWriting;

    public MoreInfoDialogFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);

        System.out.println("sharedPref size is: " + sharedPref.getAll().size());

        for(int i = 0; i < sharedPref.getAll().size(); i++){
            schoolPhoneNumber = sharedPref.getString("schoolPhoneNumber", "schoolPhoneNumber");
            schoolFaxNumber = sharedPref.getString("schoolFaxNumber", "schoolFaxNumber");
            schoolEmailStr = sharedPref.getString("schoolEmail", "schoolEmail");
            schoolLocation = sharedPref.getString("schoolLocation", "schoolLocation");
            satCritical = sharedPref.getString("satCriticalReadingAvgScore", "satCriticalReadingAvgScore");
            satMath = sharedPref.getString("satMathAvgScore", "satMathAvgScore");
            satWriting = sharedPref.getString("satWritingAvgScore", "satWritingAvgScore");
        }

        System.out.println("sharedPref from frag is " + sharedPref.getAll());
        System.out.println("schoolPhoneNumber from frag is " + schoolPhoneNumber);
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.learn_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        cardView = view.findViewById(R.id.more_info_card_view);

        satTag = view.findViewById(R.id.satTag);
        satTag.setText("SAT SCORES");

        satCriticalReadingAvgScore = view.findViewById(R.id.satCriticalTv);
        satCriticalReadingAvgScore.setText("SAT Critical: " + satCritical);

        satMathAvgScore = view.findViewById(R.id.satMathTv);
        satMathAvgScore.setText("SAT Math: " + satMath);

        satWritingAvgScore = view.findViewById(R.id.satWritingTv);
        satWritingAvgScore.setText("SAT Writing: " + satWriting);

        location = view.findViewById(R.id.locationTv);
        location.setText("Location: " + schoolLocation);

        phoneNumber = view.findViewById(R.id.phoneNumberTv);
        phoneNumber.setText("Phone Number: " + schoolPhoneNumber);

        faxNumber = view.findViewById(R.id.faxNumberTv);
        faxNumber.setText("Fax Number: " + schoolFaxNumber);

        schoolEmail = view.findViewById(R.id.schoolEmailTv);
        schoolEmail.setText("Email: " + schoolEmailStr);
    }
}