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
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.DailyExpenseViewHolder>
        implements Filterable {


    ArrayList<DailyExpense_Data> filteredList;
    ArrayList<DailyExpense_Data> list;
    private Context ctx;

    public DailyExpenseAdapter(ArrayList<DailyExpense_Data> list, Context ctx) {
        this.filteredList = list;
        this.list = list;
        this.ctx = ctx;
    }

    @NonNull
    @Override
    public DailyExpenseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.single_expense_data, null);
        return new DailyExpenseAdapter.DailyExpenseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyExpenseViewHolder dailyExpenseViewHolder, int i) {
        DailyExpense_Data data = filteredList.get(i);

        dailyExpenseViewHolder.supplier.setText(data.getSupplier());
        dailyExpenseViewHolder.total.setText("Price: "+data.getTotal());
        dailyExpenseViewHolder.purpose.setText("Purpose: \n"+data.getPurpose());
        dailyExpenseViewHolder.description.setText("Description: \n"+data.getDescription());


    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    public class DailyExpenseViewHolder extends RecyclerView.ViewHolder {
        TextView id, farmId, purpose, supplier, description, unit, total, unitPrice, date;

        public DailyExpenseViewHolder(@NonNull View itemView) {
            super(itemView);
            purpose = itemView.findViewById(R.id.purpose);
            description = itemView.findViewById(R.id.description);
            total = itemView.findViewById(R.id.total_price);
            supplier = itemView.findViewById(R.id.supplier);


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
                    ArrayList<DailyExpense_Data> filterLst = new ArrayList<>();
                    for (DailyExpense_Data row : list) {
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getPlotName().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getSupplier().toLowerCase().contains(charString.toLowerCase())) {
                            filterLst.add(row);
                        } else if (row.getPurpose().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredList = (ArrayList<DailyExpense_Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }


}
