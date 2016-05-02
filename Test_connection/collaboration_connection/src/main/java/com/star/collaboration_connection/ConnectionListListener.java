package com.star.collaboration_connection;

public interface ConnectionListListener {
    public void onNewConnection(String thread, String remoteIp);
    public void onRemoveConnection(String thread);
}
