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
import farm.gecdevelopers.com.farm.models.ItemType_Data;

public class ItemTypesAdapter extends RecyclerView.Adapter<ItemTypesAdapter.ItemTypesViewHolder>
        implements Filterable {

    ArrayList<ItemType_Data> filteredList;
    ArrayList<ItemType_Data> list;
    private Context ctx;

    public ItemTypesAdapter(Context ctx, ArrayList<ItemType_Data> list) {
        this.ctx = ctx;
        this.list = list;
        this.filteredList = list;
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

        ItemType_Data man = filteredList.get(i);

        //binding the data with the viewholder views
        itemTypesViewHolder.name.setText("" + man.getName());
        itemTypesViewHolder.manufacturer.setText("Manufacturer " + man.getManufacturer());
        itemTypesViewHolder.desc.setText("Description: " + man.getDescription());


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = list;
                } else {
                    ArrayList<ItemType_Data> filterLst = new ArrayList<>();
                    for (ItemType_Data row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getName().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getItemId().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getManufacturer().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<ItemType_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}


