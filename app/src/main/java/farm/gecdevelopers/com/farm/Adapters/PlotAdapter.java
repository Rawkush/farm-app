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
import farm.gecdevelopers.com.farm.models.Farms;
import farm.gecdevelopers.com.farm.models.Plot;

public class PlotAdapter  extends RecyclerView.Adapter<PlotAdapter.PlotViewHolder> {

    private Context ctx;
    ArrayList<Plot> list;

    public PlotAdapter(Context ctx, ArrayList<Plot> list) {
        this.ctx = ctx;
        this.list = list;
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

        Plot man = list.get(i);

        //binding the data with the viewholder views
        plotViewHolder.plotname.setText("Plot Name: "+man.getPlotname());
        plotViewHolder.size.setText("Plotsize: "+man.getSize());
        plotViewHolder.desc.setText("Description: "+man.getDesc());
        plotViewHolder.manager.setText("Manager: "+man.getDesc());
        plotViewHolder.location.setText("Location: "+man.getDesc());



    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
