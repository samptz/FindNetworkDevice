package com.example.networkdevicescanner;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class nDnsViewHolder

        extends RecyclerView.ViewHolder {
    TextView IPAddress;
    TextView DeviceName;
    View view;

    nDnsViewHolder(View itemView)
    {
        super(itemView);
        IPAddress
                = (TextView)itemView
                .findViewById(R.id.ipaddress);
        DeviceName
                = (TextView)itemView
                .findViewById(R.id.devicename);

        view = itemView;
    }
}

