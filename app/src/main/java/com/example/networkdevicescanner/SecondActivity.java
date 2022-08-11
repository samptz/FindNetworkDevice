package com.example.networkdevicescanner;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

public class SecondActivity extends AppCompatActivity {
    private DBHelper mydb ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        mydb = new DBHelper(this);
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        if (!rs.isClosed())  {
            rs.close();
        }
    }
}