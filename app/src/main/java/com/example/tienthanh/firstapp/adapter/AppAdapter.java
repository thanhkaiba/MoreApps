package com.example.tienthanh.firstapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tienthanh.firstapp.Model.App;
import com.example.tienthanh.firstapp.R;

import java.util.ArrayList;

public class AppAdapter extends ArrayAdapter {

    private ArrayList<App> apps;
    private Listener listener;

    public void setListener(Listener listener) {
        this.listener = listener;
    }

    interface Listener {
        public void onClick(String id);
    }

    public void setApps(ArrayList<App> apps) {
        this.apps = apps;
    }

    public AppAdapter(Context context, int resource, ArrayList<App> apps) {
        super(context, resource);
        this.apps = apps;
    }

    @Override
    public int getCount() {
        return apps.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final App app = apps.get(position);

        View itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_view, parent, false);
        TextView name = itemView.findViewById(R.id.app_name);
        Button download = itemView.findViewById(R.id.app_download);
        ImageView icon = itemView.findViewById(R.id.app_image);

        name.setText(app.getName());
        icon.setImageBitmap(app.getIcon());
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(app.getPkg());
            }
        });
        return itemView;
    }
}
