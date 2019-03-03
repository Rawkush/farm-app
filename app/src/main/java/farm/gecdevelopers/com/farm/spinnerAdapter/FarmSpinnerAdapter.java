package farm.gecdevelopers.com.farm.spinnerAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import farm.gecdevelopers.com.farm.R;
import farm.gecdevelopers.com.farm.models.FarmData;

public class FarmSpinnerAdapter extends
        ArrayAdapter<FarmData> {

    public FarmSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<FarmData> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getDropDownView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(int position, View convertView,
                              ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_item, parent,
                    false);
        }


        TextView main_text = (TextView) convertView
                .findViewById(R.id.item);
        if (position == 0) {
            // Set the hint text color gray
            main_text.setText("select Farm");
        } else {
            FarmData farms = getItem(position);
            main_text.setText(farms.getName());
        }


        return convertView;
    }

    @Override
    public boolean isEnabled(int position) {
        if (position == 0) {
            // Disable the first item from Spinner
            // First item will be use for hint
            return false;
        } else {
            return true;
        }
    }
}