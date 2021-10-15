package com.example.a19880516_viviankulumba_nycschools.fragments;

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

    public String schoolPhoneNumber;
    public String schoolFaxNumber;
    public String schoolEmailStr;
    public String schoolLocation;

    public MoreInfoDialogFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.learn_more, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        schoolPhoneNumber = getArguments().getString("schoolPhoneNumber");
//        schoolFaxNumber = getArguments().getString("schoolFaxNumber");
//        schoolEmailStr = getArguments().getString("schoolEmail");
//        schoolLocation = getArguments().getString("schoolLocation");

        cardView = view.findViewById(R.id.more_info_card_view);

        location = view.findViewById(R.id.locationTv);
//        location.setText(schoolLocation);

        phoneNumber = view.findViewById(R.id.phoneNumberTv);
//        phoneNumber.setText(schoolPhoneNumber);

        faxNumber = view.findViewById(R.id.faxNumberTv);
//        faxNumber.setText(schoolFaxNumber);

        schoolEmail = view.findViewById(R.id.schoolEmailTv);
//        schoolEmail.setText(schoolEmailStr);
    }
}