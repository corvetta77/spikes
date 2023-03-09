package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

class ServiceRegistryTest {

    private ServiceRegistry underTest;

    @BeforeEach
    void setUp() {
        underTest = new ServiceRegistry();
    }

    @Test
    void shouldSimplyRegister() {
        String hostname = "host1-prod";
        String location = "location";

        boolean isRegistrationSuccessfull = underTest.register(hostname, location);

        assertTrue(isRegistrationSuccessfull);
    }

    @Test
    void shouldLimitNumberOfRegistrationTo10() {
        //given
        String hostname = "host1-prod";
        String location = "location";
        int maximalNumberOfRegistrations = 11;

        //when
        for (int i = 0; i < maximalNumberOfRegistrations -1; i++) {
            boolean isRegistrationSuccessfull = underTest.register(hostname + i, location);
            assertTrue(isRegistrationSuccessfull);
        }

        //then
        try {
            underTest.register(hostname, location);
            fail();
        } catch (RegistryFullException e) {
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    void shouldNotAllowForMultipleRegistrationsOfSameHost() {
        //given
        String hostname = "host1-prod";
        String location = "location";

        //when & then
        try {
            underTest.register(hostname, location);
            underTest.register(hostname, location);
            fail();
        } catch (ServerAlreadyExistException e) {
        } catch (RuntimeException e) {
            fail();
        }
    }

    @Test
    void shouldReturnDifferentHostEachTime() {
        //given
        String hostname = "host1-prod";
        String location = "location";

        //when
        underTest.register(hostname + "1", location);
        underTest.register(hostname + "2", location);

        //when
        String host1 = underTest.getInstance();
        String host2 = underTest.getInstance();
        Set<String> registeredHostnames = underTest.getRegisteredHostnames();

        //then
        assertTrue(registeredHostnames.contains(host1));
        assertTrue(registeredHostnames.contains(host2));

    }

}