package com.star.test_connection;

import com.star.collaboration_connection.Authorizer;
import com.star.collaboration_connection.Connection;
import com.star.collaboration_connection.ConnectionInterface;
import com.star.collaboration_connection.ConnectionListener;
import com.star.collaboration_connection.InfoOfWifi;
import com.star.collaboration_connection.NetWorkInfo;

/**
 * Created by Star on 2016/5/1.
 */
public class Server implements Authorizer {
    ConnectionInterface connection = new MyServerConnection(Connection.ConnectionType.TYPE_SERVER, this);
    private String mIp;
    private int mPort = 10083;
    public Server(InfoOfWifi wifi){
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
                System.out.println("DEBUG:onConnectionStatusChanged getNetWorkInfo="
                        + connection.getNetWorkInfos());
            }
        });
        connection.start();
        Data_connection.setConnection(connection);
    }

    @Override
    public boolean authorize(String remoteIp) {
        System.out.println("remoteIp request to connection:" + remoteIp);
        String input = "yes";
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if ("yes".equals(input)) {
            return true;
        } else {
            return false;
        }
    }

    class MyServerConnection extends Connection {

        public MyServerConnection(ConnectionType type, Authorizer authorizer) {
            super(type, authorizer);
        }

        @Override
        public String getLocalHostIp() {
            return mIp;
        }

        @Override
        public int getServerBroadcastPort() {
            return 10081;
        }

        @Override
        public int getClientBroadcastPort() {
            // TODO Auto-generated method stub
            return 10082;
        }

        @Override
        public int getConnectionPort() {
            // TODO Auto-generated method stub
            return 10083;
        }
    }

    public void sendMsg(String msg){
        connection.sendDataToTarget(msg);
    }
}
