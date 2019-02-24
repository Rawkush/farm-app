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
import farm.gecdevelopers.com.farm.models.ItemType_Data;

public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ItemTypesViewHolder> {

    ArrayList<ItemType_Data> list;
    private Context ctx;

    public ItemTypesAdapter(Context ctx, ArrayList<ItemType_Data> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public ItemTypesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.sinle_item_list, null);
        return new ItemTypesAdapter.ItemTypesViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ItemTypesAdapter.ItemTypesViewHolder itemTypesViewHolder, int i) {

        ItemType_Data man = list.get(i);

        //binding the data with the viewholder views
        itemTypesViewHolder.name.setText("" + man.getName());
        itemTypesViewHolder.manufacturer.setText("Manufacturer " + man.getManufacturer());
        itemTypesViewHolder.desc.setText("Description: " + man.getDescription());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ItemTypesViewHolder extends RecyclerView.ViewHolder {
        TextView name, manufacturer, desc;

        public ItemTypesViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.item_name);
            manufacturer = itemView.findViewById(R.id.manufacture_id);
            desc = itemView.findViewById(R.id.description);


        }
    }
}


