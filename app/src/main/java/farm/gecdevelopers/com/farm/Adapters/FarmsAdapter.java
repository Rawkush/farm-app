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
import farm.gecdevelopers.com.farm.models.FarmData;

public class FarmsAdapter  extends RecyclerView.Adapter<FarmsAdapter.FarmsViewHolder> {

    private Context ctx;
    ArrayList<FarmData> list;

    public FarmsAdapter(Context ctx, ArrayList<FarmData> list) {
        this.ctx = ctx;
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

        FarmData man = list.get(i);

        //binding the data with the viewholder views
        farmsViewHolder.name.setText("Farm Name: "+man.getName());
        farmsViewHolder.size.setText("Farmsize: "+man.getSize());
        farmsViewHolder.desc.setText("Description: "+man.getDesc());



    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
