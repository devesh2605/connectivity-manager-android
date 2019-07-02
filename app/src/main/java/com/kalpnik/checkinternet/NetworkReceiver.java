package com.kalpnik.checkinternet;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NetworkReceiver extends BroadcastReceiver {

    private NetworkListener listener;

    @Override
    public void onReceive(Context context, Intent intent) {
        listener = (NetworkListener)context;
        int status = NetworkUtil.getConnectivityStatusString(context);
        if(status == 0) {
            status= 0;
        }
        listener.updateNetworkStatus(status);
    }
}