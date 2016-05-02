package com.star.collaboration_connection;

public interface Authorizer {
    public boolean authorize(String remoteIp);
}
