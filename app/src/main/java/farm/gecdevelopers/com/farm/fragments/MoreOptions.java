package farm.gecdevelopers.com.farm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.ChangePassword;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.DailyExpense;
import farm.gecdevelopers.com.farm.activity.admin.ItemTypesActivity;

public class MoreOptions extends Fragment {
    View root;

    CardView cdItemtype, cdDailyExpense, cdDailActivity, cdChangePassword, cdLogout, cdDocument;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_more_options, container, false);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cdDailyExpense = view.findViewById(R.id.daily_expense);
        cdDailActivity = view.findViewById(R.id.daily_activity);
        cdChangePassword = view.findViewById(R.id.change_password);
        cdLogout = view.findViewById(R.id.logout);
        cdItemtype = view.findViewById(R.id.item_type);
        cdDocument = view.findViewById(R.id.doc);

        init();
    }

    private void init() {

        cdDailyExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DailyExpense.class);
                startActivity(intent);
            }
        });


        cdItemtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getActivity(), ItemTypesActivity.class);
                startActivity(i);
            }
        });
        cdLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.session.logoutUser();
                //Toast.makeText(getActivity(),"Thanks for Visiting",Toast.LENGTH_SHORT).show();
            }
        });


        cdChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ChangePassword.class);
                startActivity(i);
            }
        });


        cdDailActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), DailyActivities.class);
                startActivity(i);
            }
        });


        cdDocument.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // doc reader
            }
        });


    }


}
