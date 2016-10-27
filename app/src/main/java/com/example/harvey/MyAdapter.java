package com.example.harvey;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class MyAdapter extends ArrayAdapter {
    private List<CrashName> movieModelList;
    private int resouce;
    private LayoutInflater inflater;
    public MyAdapter(Context context, int resource, List<CrashName> objects) {
        super(context, resource, objects);
        movieModelList = objects;
        this.resouce = resource;
        inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView ==null){
            convertView = inflater.inflate(resouce, null);
        }

        TextView tvName;
        tvName =(TextView) convertView.findViewById(R.id.name);
        tvName.setText(movieModelList.get(position).getName());
        return convertView;
    }
}
