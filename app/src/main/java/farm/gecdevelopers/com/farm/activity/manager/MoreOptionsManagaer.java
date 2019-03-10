package farm.gecdevelopers.com.farm.activity.manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.ChangePassword;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
public class MoreOptionsManagaer extends Fragment {
    View root;

    FragmentManager fManager;
    FragmentTransaction fTransaction;
    CardView cdLogout, cdChangePassword;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_more_options_man, container, false);





        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        cdChangePassword = view.findViewById(R.id.change_password);
        cdLogout = view.findViewById(R.id.logout_btn);

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

    }
}
