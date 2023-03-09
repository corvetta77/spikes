package org.example;

import java.util.*;
import java.util.stream.Collectors;

public class ServiceRegistry {

    private final List<ServerRepresentation> registry = new ArrayList<>();

    public boolean register(String hostname, String location) throws ServerAlreadyExistException {

        synchronized (registry) {
            if (registry.size() == 10) {
                throw new RegistryFullException("Registry has limit of 10");
            }
            for (ServerRepresentation serverRepresentation : registry) {
                if (serverRepresentation.hostname() == hostname) {
                    throw new ServerAlreadyExistException("Registry has limit of 10");
                }
            }
            registry.add(new ServerRepresentation(hostname, location));
        }

        return true;
    }

    public Set<String> getRegisteredHostnames() {
        Set<String> strings;
        synchronized (registry) {
            strings = registry.stream()
                    .map(ServerRepresentation::hostname)
                    .collect(Collectors.toSet());
        }
        return strings;
    }

    public String getInstance() {
        String hostname;
        synchronized (registry) {
            int rand = new Random().nextInt(registry.size());
            hostname = registry.get(rand).hostname();
        }
        return hostname;
    }
}