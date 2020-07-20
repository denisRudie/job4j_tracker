package ru.job4j.tracker.stream;

import java.util.Arrays;

public class CrossArray {

    public static void printCrossEl(int[] left, int[] right) {

        Arrays.stream(left)
                .distinct()
                .flatMap(i1 -> Arrays.stream(right)
                        .distinct()
                        .filter(i2 -> i1 == i2))
                .forEach(System.out::println);
    }
}
