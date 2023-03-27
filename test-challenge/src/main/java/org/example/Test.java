package org.example;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.IntStream.range;

public class Test {

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        t1.start();

        Thread t2 = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        t2.join(100);
        t2.start();
        Thread.sleep(1000);
    }

    private static final int LOWER_BOUNDARY_FOR_PRIMES = 2;

    public boolean isEven(int i) {
        return i % 2 == 0;
    }

    public boolean isPrime(Integer number) {
        return Optional.ofNullable(number)
                .map(integer -> {
                    if (integer < LOWER_BOUNDARY_FOR_PRIMES) {
                        return false;
                    }
                    return range(LOWER_BOUNDARY_FOR_PRIMES, integer)
                            .noneMatch(divider -> integer % divider == 0);
                })
                .orElse(false);

    }

    public String concatStrings(String[] array) {
        return Arrays.stream(array)
                .filter(Objects::nonNull)
                .filter(this::notANumber)
                .reduce("", (current, next) -> current + next);
    }

    public String concatStringsWithCollect(String[] array) {
        return Arrays.stream(array)
                .filter(Objects::nonNull)
                .filter(this::notANumber)
                .collect(Collectors.joining());
    }

    private boolean notANumber(String s) {
        try {
            Integer.parseInt(s);
            return false;
        } catch (Exception e) {
            return true;
        }
    }
}