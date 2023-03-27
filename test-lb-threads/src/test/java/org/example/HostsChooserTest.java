package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HostsChooserTest {
    private HostsChooser underTest;

    @BeforeEach
    void setUp() {
        underTest = new HostsChooser();
    }

    @Test
    void shouldPickOneOfServers() {
        //given
        List<String> serverNames = List.of("server1", "server2");

        //when
        String destinationServer = underTest.findOne(serverNames);

        //then
        System.out.println(destinationServer);
        assertTrue(serverNames.contains(destinationServer));
    }
}