package com.kalpnik.checkinternet;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements NetworkListener {

    private BroadcastReceiver MyReceiver = null;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyReceiver = new NetworkReceiver();
        textView = (TextView) findViewById(R.id.connection);

        broadcastIntent();
    }

    public void broadcastIntent() {
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    public void updateTheTextView(int status) {
        switch (status){
            case 0:
                textView.setText("Connection: "+"No Internet");
                break;
            case 1:
                textView.setText("Connection: "+"Wifi");
                break;
            case 2:
                textView.setText("Connection: "+"Mobile");
                break;
                default:
                    textView.setText("Connection: "+"Unknown");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(MyReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(MyReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void updateNetworkStatus(int result) {
        updateTheTextView(result);
    }
}