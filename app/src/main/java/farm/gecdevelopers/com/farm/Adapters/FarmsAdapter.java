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
import farm.gecdevelopers.com.farm.models.FarmData;

public class FarmsAdapter extends RecyclerView.Adapter<FarmsAdapter.FarmsViewHolder>
        implements Filterable {

    private Context ctx;
    ArrayList<FarmData> filteredList;
    ArrayList<FarmData> list;

    public FarmsAdapter(Context ctx, ArrayList<FarmData> list) {
        this.ctx = ctx;
        this.filteredList = list;
        this.list = list;
    }

    @NonNull
    @Override
    public FarmsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_farm_list, null);
        return new FarmsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FarmsViewHolder farmsViewHolder, int i) {

        FarmData man = filteredList.get(i);

        //binding the data with the viewholder views
        farmsViewHolder.name.setText("Farm Name: "+man.getName());
        farmsViewHolder.size.setText("Farmsize: "+man.getSize());
        farmsViewHolder.desc.setText("Description: "+man.getDesc());



    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class FarmsViewHolder extends RecyclerView.ViewHolder {
        TextView name,size, desc;

        public FarmsViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.farm_name);
            size=itemView.findViewById(R.id.size_id);
            desc=itemView.findViewById(R.id.description);



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
                    ArrayList<FarmData> filterLst = new ArrayList<>();
                    for (FarmData row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<FarmData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
