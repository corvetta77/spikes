package org.example;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class HostConfiguration {
    public static final ReentrantLock LOCK = new ReentrantLock();
    private final List<String> availableHosts;

    public HostConfiguration(List<String> availableHosts) {
        this.availableHosts = availableHosts;
    }

    public void registerNewHost(String newHostName) {
        while (true) {
            if (LOCK.tryLock()) {
                availableHosts.add(newHostName);
                LOCK.unlock();
                break;
            }
        }
    }

    public List<String> getHosts() {
        while (true) {
            boolean isLockAcquired = false;
            try {
                isLockAcquired = LOCK.tryLock();
            } finally {
                if (isLockAcquired) {
                    return availableHosts;
                }
            }
        }
    }

}
