package com.example.metro.volleyexample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Metro on 07-04-2017.
 */
public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.MyViewHolder> {

    ArrayList<Parkinglist> arrayList = new ArrayList<Parkinglist>();

    public RecycleAdapter(ArrayList<Parkinglist> arrayList) {
        this.arrayList = arrayList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.locationname.setText(arrayList.get(position).getLocationName());
        holder.locationid.setText(arrayList.get(position).getLocationID());
        holder.latitude.setText(arrayList.get(position).getLatitude());
        holder.longitude.setText(arrayList.get(position).getLongitude());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView locationname,locationid,latitude,longitude;
        public MyViewHolder(View itemView) {
            super(itemView);
            locationname = (TextView)itemView.findViewById(R.id.locationname);
            locationid = (TextView)itemView.findViewById(R.id.locationid);
            latitude = (TextView)itemView.findViewById(R.id.latitude);
            longitude = (TextView)itemView.findViewById(R.id.longitude);
        }
    }
}
