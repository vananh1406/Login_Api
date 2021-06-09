package com.example.login_api;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends BaseAdapter {
    private Context context;
    private ArrayList<Employee> empl;
    private int layout;

    public Adapter(Context context, ArrayList<Employee> empl, int layout) {
        this.context = context;
        this.empl = empl;
        this.layout = layout;

    }

    @Override
    public int getCount() {
        return empl.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(layout, parent, false);
        TextView tvId = view.findViewById(R.id.tvId);
        TextView tvName = view.findViewById(R.id.tvNamee);
        tvId.setText(empl.get(position).getId()+ "");
        tvName.setText(empl.get(position).getName());

        return view;
    }


}
