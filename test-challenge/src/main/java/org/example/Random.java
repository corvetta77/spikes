package org.example;

import java.util.List;

public class Random {

    private List<String> registeredServers;

    public Random(List<String> registeredServers) {
        this.registeredServers = registeredServers;
    }

    public String getServer(String clientIp) {
        return registeredServers.get(new java.util.Random().nextInt(2));
    }
}
