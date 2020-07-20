package ru.job4j.tracker.stream;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Merge {

    public static int[] merge(int[] left, int[] right) {
        return IntStream.concat(
                Arrays.stream(left),
                Arrays.stream(right))
                .sorted()
                .toArray();
    }
}
