package org.example;

import java.util.List;

public class LBConfig {
    List<String> registeredServers;

    public LBConfig(List<String> registeredServers) {
        this.registeredServers = registeredServers;
    }

    public List<String> getRegisteredServers() {
        return registeredServers;
    }
}
