package com.example.administrator.myapplication.adapters;

import android.app.Activity;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.administrator.myapplication.R;

import java.util.List;

public class ProfileListViewAdapter extends ArrayAdapter<Pair<String, String>> {
    private final Activity context;
    private final List<Pair<String, String>> values;

    public ProfileListViewAdapter(Activity context, List<Pair<String, String>> values) {
        super(context, R.layout.profile_row, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.profile_row, null);

            viewHolder = new ViewHolder();
            viewHolder.key = (TextView) convertView.findViewById(R.id.profileRowKey);
            viewHolder.value = (TextView) convertView.findViewById(R.id.profileRowValue);
            convertView.setTag(viewHolder);
        } else viewHolder = (ViewHolder) convertView.getTag();
        Pair<String, String> pair = values.get(position);
        viewHolder.key.setText(pair.first);
        viewHolder.value.setText(pair.second);
        return convertView;
    }

    static class ViewHolder {
        public TextView key;
        public TextView value;
    }
}
