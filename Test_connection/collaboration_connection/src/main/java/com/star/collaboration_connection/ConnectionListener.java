package com.star.collaboration_connection;

import com.star.collaboration_connection.NetWorkInfo.ConnectStatus;

public interface ConnectionListener {
    public void onNewDataReceive(Object data);
    public void onNewDataReceive(String data);
    public void onConnectionStatusChanged(ConnectStatus newStatus);
}
