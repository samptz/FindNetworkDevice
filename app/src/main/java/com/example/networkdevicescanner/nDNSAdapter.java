package com.example.networkdevicescanner;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class nDNSAdapter
        extends RecyclerView.Adapter<nDnsViewHolder> {

    ArrayList<mDNSData> list=new ArrayList<mDNSData>();


    Context context;
    ClickListiner listiner;

    public nDNSAdapter(ArrayList<mDNSData> list,
                       Context context, ClickListiner listiner)
    {
        this.list = list;
        this.context = context;
        this.listiner = listiner;
    }

    @Override
    public nDnsViewHolder
    onCreateViewHolder(ViewGroup parent,
                       int viewType)
    {

        Context context
                = parent.getContext();
        LayoutInflater inflater
                = LayoutInflater.from(context);

        // Inflate the layout

        View photoView
                = inflater
                .inflate(R.layout.ndns_card,
                        parent, false);

        nDnsViewHolder viewHolder
                = new nDnsViewHolder(photoView);
        return viewHolder;
    }

    @Override
    public void
    onBindViewHolder(final nDnsViewHolder viewHolder,
                     final int position)
    {
        final int index = viewHolder.getAdapterPosition();
        viewHolder.IPAddress
                .setText(list.get(position).IPAddress);
        viewHolder.DeviceName
                .setText(list.get(position).DeviceName);
        viewHolder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {


                listiner.click(index);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(
            RecyclerView recyclerView)
    {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
