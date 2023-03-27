package org.example;

import java.util.List;

public class RoundRobin implements LB {
    private static Integer position = 0;
    private final List<String> servers;

    public RoundRobin(List<String> registeredServers) {
        servers = registeredServers;
    }

    @Override
    public String getServer(String clientIp) {
        String target;

        synchronized (position) {
            if (position > servers.size() - 1) {
                position = 0;
            }
            target = servers.get(position);
            position++;
        }
        return target;
    }
}