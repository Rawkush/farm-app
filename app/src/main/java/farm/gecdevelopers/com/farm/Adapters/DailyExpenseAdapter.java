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
import farm.gecdevelopers.com.farm.models.DailyExpense_Data;

public class DailyExpenseAdapter extends RecyclerView.Adapter<DailyExpenseAdapter.DailyExpenseViewHolder> {


    ArrayList<DailyExpense_Data> list;
    private Context ctx;

    public DailyExpenseAdapter(ArrayList<DailyExpense_Data> list, Context ctx) {
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
        DailyExpense_Data data = list.get(i);

        dailyExpenseViewHolder.supplier.setText(data.getSupplier());
        dailyExpenseViewHolder.total.setText(data.getTotal());
        dailyExpenseViewHolder.purpose.setText(data.getPurpose());
        dailyExpenseViewHolder.description.setText(data.getDescription());


    }

    @Override
    public int getItemCount() {
        return list.size();
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
}
