package org.example;

import java.util.List;
import java.util.Random;

public class HostsChooser {

    public static final Random RANDOM_GENERATOR = new Random();

    public String findOne(List<String> serverNames) {
        return serverNames.get(RANDOM_GENERATOR.nextInt(serverNames.size()));
    }
}
