package com.star.test_connection;

import com.star.collaboration_connection.Connection;
import com.star.collaboration_connection.ConnectionInterface;
import com.star.collaboration_connection.ConnectionListener;
import com.star.collaboration_connection.InfoOfWifi;
import com.star.collaboration_connection.NetWorkInfo;

/**
 * Created by Star on 2016/5/2.
 */
public class Client {
    private ConnectionInterface connection = new MyClientConnection(Connection.ConnectionType.TYPE_CLIENT);
    private String mIp;
    private int mPort = 10083;
    public Client(InfoOfWifi wifi){
        mIp = wifi.getIp();
    }

    public void start() {
        connection.registConnectionListener(new ConnectionListener() {

            @Override
            public void onNewDataReceive(String data) {
                System.out.println("DEBUG:onNewDataReceive message=" + data);
            }

            @Override
            public void onNewDataReceive(Object data) {
                System.out.println("DEBUG:onNewDataReceive object=" + data);
            }

            @Override
            public void onConnectionStatusChanged(NetWorkInfo.ConnectStatus newStatus) {
                System.out.println("DEBUG:onConnectionStatusChanged newStatus=" + newStatus);
                System.out.println("DEBUG:onConnectionStatusChanged getNetWorkInfo=" + connection.getNetWorkInfo());
            }
        });
        connection.start();
        Data_connection.setConnection(connection);
    }


    class MyClientConnection extends Connection {

        public MyClientConnection(ConnectionType type) {
            super(type);
        }

        @Override
        public int getServerBroadcastPort() {
            return 10081;
        }

        @Override
        public int getClientBroadcastPort() {
            return 10082;
        }

        @Override
        public int getConnectionPort() {
            return 10083;
        }

        @Override
        public String getLocalHostIp() {
            return mIp;
        }

        @Override
        protected String getDeviceMac() {
            return "1234567890";
        }

        @Override
        protected String getDeviceName() {
            // TODO Auto-generated method stub
            return "test";
        }
    }

    public void sendMsg(String msg){
        connection.sendDataToTarget(msg);
    }
}
