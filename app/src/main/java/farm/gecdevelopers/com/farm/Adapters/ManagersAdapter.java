package farm.gecdevelopers.com.farm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.Managers;

public class ManagersAdapter  extends RecyclerView.Adapter<ManagersAdapter.ManagersViewHolder>
        implements Filterable {

    private Context ctx;
    ArrayList<Managers> filteredList;
    ArrayList<Managers> list;

    public ManagersAdapter(Context ctx, ArrayList<Managers> list) {
        this.ctx = ctx;
        this.filteredList = list;
        this.list=list;
    }

    @NonNull
    @Override
    public ManagersViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_manager_item, null);
        return new ManagersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManagersViewHolder managersViewHolder, int i) {

        Managers man = filteredList.get(i);

        //binding the data with the viewholder views
        managersViewHolder.name.setText(man.getManName());
        managersViewHolder.username.setText("Username: "+man.getManUsrname());
        managersViewHolder.id.setText("Manager Id: "+man.getManId());
        managersViewHolder.email.setText("Email: "+man.getManEmail());
        managersViewHolder.phn.setText("Contact: "+man.getManPhnNo());

        managersViewHolder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"edit Clicked",Toast.LENGTH_SHORT).show();
            }
        });
        managersViewHolder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,"Delete Clicked",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class ManagersViewHolder extends RecyclerView.ViewHolder {
        TextView name,id,email,username,phn;
        ImageView del,edit;
        public ManagersViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            username=itemView.findViewById(R.id.uname);
            id=itemView.findViewById(R.id.man_id);
            email=itemView.findViewById(R.id.man_email);
            phn=itemView.findViewById(R.id.man_phn_no);
            del=itemView.findViewById(R.id.delete_btn);
            edit=itemView.findViewById(R.id.edit_btn);

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
                    ArrayList<Managers> filterLst = new ArrayList<>();
                    for (Managers row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getManName().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else
                        if (row.getManEmail().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<Managers>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }




}
