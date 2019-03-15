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
import farm.gecdevelopers.com.farm.models.PlotData;

public class PlotAdapter extends RecyclerView.Adapter<PlotAdapter.PlotViewHolder>
        implements Filterable {

    private Context ctx;
    ArrayList<PlotData> filteredList;
    ArrayList<PlotData> list;

    public PlotAdapter(Context ctx, ArrayList<PlotData> list) {
        this.ctx = ctx;
        this.list = list;
        this.filteredList = list;
    }

    @NonNull
    @Override
    public PlotViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_plot_list, null);
        return new PlotViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlotViewHolder plotViewHolder, int i) {

        PlotData man = filteredList.get(i);

        //binding the data with the viewholder views
        plotViewHolder.plotname.setText("PlotData Name: " + man.getPlotname());
        plotViewHolder.size.setText("Plotsize: "+man.getSize());
        plotViewHolder.desc.setText("Description: "+man.getDesc());
        plotViewHolder.manager.setText("Manager: "+man.getDesc());
        plotViewHolder.location.setText("Location: "+man.getDesc());



    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class PlotViewHolder extends RecyclerView.ViewHolder {
        TextView plotname,size, desc , manager, location;

        public PlotViewHolder(@NonNull View itemView) {
            super(itemView);

            plotname=itemView.findViewById(R.id.plot_name);
            size=itemView.findViewById(R.id.size_id);
            desc=itemView.findViewById(R.id.description);
            manager=itemView.findViewById(R.id.plotmanager);
            location=itemView.findViewById(R.id.location_id);



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
                    ArrayList<PlotData> filterLst = new ArrayList<>();
                    for (PlotData row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPlotname().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getManager().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<PlotData>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

}
