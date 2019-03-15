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
import farm.gecdevelopers.com.farm.models.DailyActivity_Data;

public class DailyActivitiesAdapter extends RecyclerView.Adapter<DailyActivitiesAdapter.DailyActivitiesViewHolder>
        implements Filterable {

    private ArrayList<DailyActivity_Data> list;
    private ArrayList<DailyActivity_Data> filteredList;
    private Context ctx;

    public DailyActivitiesAdapter(Context ctx, ArrayList<DailyActivity_Data> list) {
        this.list = list;
        this.filteredList = list;
        this.ctx = ctx;
    }




    @NonNull
    @Override
    public DailyActivitiesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_activity_man, null);
        return new DailyActivitiesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyActivitiesViewHolder dailyActivitiesViewHolder, int i) {

        DailyActivity_Data data = filteredList.get(i);
        dailyActivitiesViewHolder.plotName.setText(data.getPlotName());
       // dailyActivitiesViewHolder.location.setText(data.getHect());
        dailyActivitiesViewHolder.activityTitle.setText(data.getActid());
        dailyActivitiesViewHolder.date.setText(data.getDateAndTime());
        dailyActivitiesViewHolder.details.setText("Details: \n"+data.getDetails());
        dailyActivitiesViewHolder.comments.setText("Comments:\n"+data.getComments());
        dailyActivitiesViewHolder.areaCovered.setText("Areas Covered: \n"+data.getHect());


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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
                    ArrayList<DailyActivity_Data> filterLst = new ArrayList<>();
                    for (DailyActivity_Data row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPlotName().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getDailyAcitvityID().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<DailyActivity_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class DailyActivitiesViewHolder extends RecyclerView.ViewHolder {

        TextView plotName,location,date,activityTitle,details,comments,areaCovered;

        public DailyActivitiesViewHolder(@NonNull View itemView) {
            super(itemView);
            plotName=itemView.findViewById(R.id.plotname);
            location=itemView.findViewById(R.id.location);
            date=itemView.findViewById(R.id.date_time);
            activityTitle=itemView.findViewById(R.id.activity_title);
            details=itemView.findViewById(R.id.deatils);
            comments=itemView.findViewById(R.id.comments);
            areaCovered=itemView.findViewById(R.id.area_covered);
            //plotName=itemView.findViewById(R.id.plotname);
        }
    }


}
