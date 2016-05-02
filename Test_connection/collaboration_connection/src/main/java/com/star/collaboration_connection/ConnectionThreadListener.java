
package com.star.collaboration_connection;

import com.star.collaboration_connection.NetWorkInfo.ConnectStatus;

interface ConnectionThreadListener {
    public void onNewDataReceived(String threadName, Object data);
    public void onConnectionStatutChange(String threadName, ConnectStatus newStatus);
}
