package farm.gecdevelopers.com.farm.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.Managers;

public class ManagersAdapter  extends RecyclerView.Adapter<ManagersAdapter.ManagersViewHolder> {

    private Context ctx;
    ArrayList<Managers> list;

    public ManagersAdapter(Context ctx, ArrayList<Managers> list) {
        this.ctx = ctx;
        this.list = list;
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

        Managers man = list.get(i);

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
        return list.size();
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
}
