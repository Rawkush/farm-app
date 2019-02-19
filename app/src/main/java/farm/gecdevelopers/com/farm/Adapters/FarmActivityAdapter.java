package farm.gecdevelopers.com.farm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.FarmActivity;

public class FarmActivityAdapter extends RecyclerView.Adapter<FarmActivityAdapter.FarmActivityViewHolder> {

    private Context ctx;
    ArrayList<FarmActivity> list;

    public FarmActivityAdapter(Context ctx, ArrayList<FarmActivity> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public FarmActivityViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_farmactivity_list, null);
        return new FarmActivityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmActivityViewHolder farmActivityViewHolder, int i) {

        FarmActivity man = list.get(i);

        //binding the data with the viewholder views

        farmActivityViewHolder.activity.setText("Activity Name: "+man.getActivity());
        farmActivityViewHolder.desc.setText("Description: "+man.getDesc());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class FarmActivityViewHolder extends RecyclerView.ViewHolder {
        TextView desc , activity;

        public FarmActivityViewHolder(@NonNull View itemView) {
            super(itemView);

           ;

            desc=itemView.findViewById(R.id.description_id);
            activity=itemView.findViewById(R.id.activity_name);



        }
    }
}
