package com.example.networkdevicescanner;

public class mDNSData {
    String IPAddress;
    String DeviceName;

    mDNSData(String IP,
             String Device
             )
    {
        this.IPAddress = IP;
        this.DeviceName = Device;
    }
}

