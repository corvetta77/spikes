package org.example;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRobin2 {

    private Integer counter = 0;

    private final List<String> registeredServers;
    private Lock lock = new ReentrantLock();

    public RoundRobin2(List<String> registeredServers) {
        this.registeredServers = registeredServers;
    }

    public String getServer(String clientIp) {
        String server = null;
        while (true) {
            if (lock.tryLock()) {
                if (counter >= registeredServers.size()) {
                    counter = 0;
                }
                server = registeredServers.get(counter);
                counter++;
                lock.unlock();
                break;
            }
        }
        return server;
    }
}
