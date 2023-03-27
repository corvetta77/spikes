package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundRobin2EvenOrOddTest {
    private RoundRobin2 underTest;
    private List<String> registeredServers = List.of("ip1", "ip2");

    @BeforeEach
    void setUp() {
        underTest = new RoundRobin2(registeredServers);
    }

    @Test
    void testRoundRobin() {
        long milis = System.currentTimeMillis();
        String result1 = underTest.getServer("clientIp");
        assertTrue(registeredServers.contains(result1));

        String result2 = underTest.getServer("clientIp");
        assertTrue(registeredServers.contains(result2));

        assertNotEquals(result1, result2);
        System.out.println(System.currentTimeMillis() - milis);
    }

}