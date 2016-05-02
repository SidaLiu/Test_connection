package com.star.test_connection;

import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.star.collaboration_connection.ConnectionInterface;
import com.star.collaboration_connection.InfoOfWifi;


public class Data_connection extends AppCompatActivity implements View.OnClickListener {
    private WifiManager wifiManager ;
    private InfoOfWifi wifi;
    private boolean asServer = false;
    private boolean asClient = false;
    private EditText sendMsg;
    private static ConnectionInterface mConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.activity_data_connection);
        wifiManager = (WifiManager) getSystemService(WIFI_SERVICE);
        wifi = new InfoOfWifi(wifiManager);
        Button serverBtn = (Button) findViewById(R.id.server_btn);
        serverBtn.setOnClickListener(this);
        Button clientBtn = (Button) findViewById(R.id.client_btn);
        clientBtn.setOnClickListener(this);
        Button sendBtn = (Button) findViewById(R.id.send_btn);
        sendBtn.setOnClickListener(this);
        sendMsg = (EditText) findViewById(R.id.edit);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.server_btn:
                startAsServer();
                break;
            case R.id.client_btn:
                startAsClient();
                break;
            case R.id.send_btn:
                sendMsg();
                break;
            default:
                break;
        }
    }

    private void sendMsg() {
        String msg = sendMsg.getText().toString();
        if(msg != null){
            mConnection.sendDataToTarget(msg);
        }
    }

    private void startAsClient() {
        if(asServer){
            return;
        }
        asClient = true;
        Client client = new Client(wifi);
        client.start();
    }

    private void startAsServer() {
        if (asClient ){
            return;
        }
        asServer = true;
        Server server = new Server(wifi);
        server.start();
    }

    public static void setConnection(ConnectionInterface connection){
        mConnection = connection;
    }
}
