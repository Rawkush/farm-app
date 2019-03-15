package farm.gecdevelopers.com.farm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.FarmActivityData;

public class FarmActivityAdapter extends RecyclerView.Adapter<FarmActivityAdapter.FarmActivityViewHolder>
        implements Filterable {

    private Context ctx;
    ArrayList<FarmActivityData> list;
    ArrayList<FarmActivityData> filteredList;

    public FarmActivityAdapter(Context ctx, ArrayList<FarmActivityData> list) {
        this.ctx = ctx;
        this.list = list;
        this.filteredList = list;
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

        FarmActivityData man = filteredList.get(i);

        //binding the data with the viewholder views

        farmActivityViewHolder.activity.setText("Activity Name: "+man.getActivity());
        farmActivityViewHolder.desc.setText("Description: "+man.getDesc());


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = list;
                } else {
                    ArrayList<FarmActivityData> filterLst = new ArrayList<>();
                    for (FarmActivityData row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getActivity().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getId().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        }


                    }

                    filteredList = filterLst;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<FarmActivityData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
