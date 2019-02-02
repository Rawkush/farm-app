package farm.gecdevelopers.com.farm.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.activity.SplashActivity;
import farm.gecdevelopers.com.farm.activity.admin.ItemTypesActivity;

public class MoreOptions extends Fragment {
    View root;

    ImageView logout,itemTypes ;
FrameLayout frame;
    FragmentManager fManager;
    FragmentTransaction fTransaction;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_more_options, container, false);

        frame=root.findViewById(R.id.frame);


        logout=root.findViewById(R.id.logout_btn);
        itemTypes=root.findViewById(R.id.item_types_btn);





        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        itemTypes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(getActivity(),ItemTypesActivity.class);
                startActivity(i);
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SplashActivity.session.logoutUser();

                //Toast.makeText(getActivity(),"Thanks for Visiting",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
