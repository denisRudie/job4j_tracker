package ru.job4j.tracker.stream;

import java.util.Arrays;

public class ConcatArray {
    public static int combineSize(int[] a, int[] b) {

        return (int) (Arrays.stream(a)
                .count()
                + Arrays.stream(b)
                .count());
    }
}
