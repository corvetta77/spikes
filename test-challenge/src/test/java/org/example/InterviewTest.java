package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterviewTest {

    private Test underTest;

    @BeforeEach
    void setUp() {
        underTest = new Test();
    }

    @Test
    void shouldVerifyEvenNumbers() {
        assertTrue(underTest.isEven(2));
        assertTrue(underTest.isEven(4));
        assertTrue(underTest.isEven(88));
    }

    @Test
    void shouldVerifyOddNumbers() {
        Assertions.assertFalse(underTest.isEven(1));
        Assertions.assertFalse(underTest.isEven(7));
        Assertions.assertFalse(underTest.isEven(99));
    }

    @Test
    void shouldFailWhenBelowTwo() {
        Assertions.assertFalse(underTest.isPrime(0));
        Assertions.assertFalse(underTest.isPrime(1));
    }

    @Test
    void shouldFailWhenNotPrimeNumber() {
        Assertions.assertFalse(underTest.isPrime(4));
        Assertions.assertFalse(underTest.isPrime(6));
        Assertions.assertFalse(underTest.isPrime(58));
    }

    @Test
    void shouldSucceedWhenPrimeNumber() {
        assertTrue(underTest.isPrime(2));
        assertTrue(underTest.isPrime(7));
        assertTrue(underTest.isPrime(17));
    }

    @Test
    void shouldFailGracefullyInCaseOfNull() {
        assertFalse(underTest.isPrime(null));
    }

    @Test
    void testArrayOfStrings() {
        String[] array = {"a", "b", "c"};
        String resultOfConcatenation = underTest.concatStrings(array);
        assertTrue(resultOfConcatenation.equals("abc"));
    }

    @Test
    void testArrayOfStrings_filterNulls() {
        String[] array = {null, "a", "b", "c", null};
        String resultOfConcatenation = underTest.concatStrings(array);
        assertTrue(resultOfConcatenation.equals("abc"));
    }

    @Test
    void testArrayOfStrings_filterNumbers() {
        String[] array = {"1", "a", "b", "c", "12"};
        String resultOfConcatenation = underTest.concatStrings(array);
        assertTrue(resultOfConcatenation.equals("abc"));
    }

    @Test
    void testArrayOfStrings_filterNumbers_withCollect() {
        String[] array = {"1", "a", "b", "c", "12"};
        String resultOfConcatenation = underTest.concatStringsWithCollect(array);
        assertTrue(resultOfConcatenation.equals("abc"));
    }

    @Test
    void testArrayOfStrings_filterNumbers_withCollect_() {

    }
}