package com.star.collaboration_connection;

import android.net.DhcpInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

/**
 * Created by Star on 2016/4/23.
 */
public class InfoOfWifi {
    final static String TAG = "WifiInfo";
    private DhcpInfo dhcpInfo;
    private WifiInfo wifiInfo;
    private String ip = null;
    private String mServerAddress = null;
    private WifiManager mWifiManager;

    public InfoOfWifi(WifiManager wifiManager){
        mWifiManager = wifiManager;
        dhcpInfo = mWifiManager.getDhcpInfo();
        wifiInfo = mWifiManager.getConnectionInfo();
        ip = intToIp(wifiInfo.getIpAddress());
        mServerAddress = intToIp(dhcpInfo.serverAddress);
    }

    private String intToIp(int address) {
        return (address & 0xFF) + "." + ((address >> 8) & 0xFF) + "." +
                ((address >> 16) & 0xFF) + "." + ((address >> 24) & 0xFF);
    }

    public String getIp(){
        return ip;
    }

    public String getSeverAddress(){
        return mServerAddress;
    }
}
