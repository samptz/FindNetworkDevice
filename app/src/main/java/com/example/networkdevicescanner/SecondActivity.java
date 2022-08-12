package com.example.networkdevicescanner;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import org.apache.http.conn.ssl.SSLSocketFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SecondActivity extends AppCompatActivity {
    private DBHelper mydb ;
    TextView txtpublicIP, txtIPDetails;
    String strAPILink = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        txtpublicIP = findViewById(R.id.txtpublicIP);
        txtIPDetails = findViewById(R.id.txtIPDetails);
        /*mydb = new DBHelper(this);
        Cursor rs = mydb.getData(1);
        rs.moveToFirst();
        if (!rs.isClosed())  {
            rs.close();
        }*/
        txtpublicIP.setText("");
        txtIPDetails.setText("");
        if(txtpublicIP.getText().equals(""))
        {
            strAPILink="https://api.ipify.org/?=jason";
            new MyTask().execute();
        }
        if(!txtpublicIP.getText().equals("")) {
            strAPILink = "https://ipinfo.io/" + txtpublicIP.getText().toString() + "/geo";
            new MyTask().execute();
        }





    }
    private class MyTask extends AsyncTask<Void, Void, Void> {
        String result;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            try {
                url = new URL(strAPILink);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()));
                String stringBuffer;
                String string = "";
                while ((stringBuffer = bufferedReader.readLine()) != null){
                    string = String.format("%s%s", string, stringBuffer);
                }
                bufferedReader.close();
                result = string;
            } catch (IOException e){
                e.printStackTrace();
                result = e.toString();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            if(txtpublicIP.getText().equals("")) {
                txtpublicIP.setText(result);
            }
            if(!txtpublicIP.getText().equals("")) {
                txtIPDetails.setText(result);
            }
            super.onPostExecute(aVoid);
        }
    }
    private static void trustAllHosts()
    {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager()
        {
            public java.security.cert.X509Certificate[] getAcceptedIssuers()
            {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException
            {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException
            {
            }
        } };

        // Install the all-trusting trust manager
        try
        {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}