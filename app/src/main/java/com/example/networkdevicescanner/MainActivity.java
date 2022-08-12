package com.example.networkdevicescanner;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    nDNSAdapter adapter;
    RecyclerView recyclerView;
    ClickListiner listiner;
    private DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHelper(this);
        ArrayList<mDNSData> list = new ArrayList<>();
        //list = getData();
        list=scanSubNet("192.168.1.");
        if(!list.isEmpty())
        {
            if(list.size()>0)
            {
                for(int i=0; i<list.size(); i++) {
                    boolean bool=mydb.insertContact(list.get(i).IPAddress,list.get(i).DeviceName);
                }

            }

        }
        ArrayList<mDNSData> list1 = new ArrayList<>();
        list1=mydb.getnetworkDevices();


       recyclerView
                = (RecyclerView)findViewById(
                R.id.recyclerView);
        listiner = new ClickListiner() {
            @Override
            public void click(int index){
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
                // Toast.makeText(this,"clicked item index is "+index,Toast.LENGTH_LONG).show();
            }
        };
        adapter= new nDNSAdapter(
                list1, getApplication(),listiner);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(MainActivity.this));

        //ArrayList<String> hosts = scanSubNet("192.168.1.");
    }
    // Sample data for RecyclerView
    private List<mDNSData> getData()
    {
        List<mDNSData> list = new ArrayList<>();
        list.add(new mDNSData("192.168.1.1",
                "Samsung Phone"
                ));
        list.add(new mDNSData("192.168.1.2",
                "IQOO Phone"
                ));


        return list;
    }
    private ArrayList<mDNSData> scanSubNet(String subnet){
        ArrayList<mDNSData> hosts = new ArrayList<mDNSData>();
        hosts.add(new mDNSData("192.168.1.1",
                "Samsung Phone"
        ));
        hosts.add(new mDNSData("192.168.1.2",
                "IQOO Phone"
        ));
        /*InetAddress inetAddress = null;
        for(int i=1; i<10; i++){
            Log.d(TAG, "Trying: " + subnet + String.valueOf(i));
            try {
                inetAddress = InetAddress.getByName(subnet + String.valueOf(i));

                    if (inetAddress.isReachable(10000)) {
                        hosts.add(inetAddress.getHostName());
                        Log.d(TAG, inetAddress.getHostName());
                    }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/

        return hosts;
    }
}