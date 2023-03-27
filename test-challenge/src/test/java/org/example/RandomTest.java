package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RandomTest {

    private Random underTest;
    private List<String> registeredServers = List.of("ip1", "ip2");

    @BeforeEach
    void setUp() {
        underTest = new Random(registeredServers);
    }

    @Test
    void testRoundRobinLb() {
        String result = underTest.getServer("clientIp");

        assertTrue(registeredServers.contains(result));
    }
}