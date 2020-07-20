package ru.job4j.tracker.stream;

import java.util.stream.IntStream;

public class Sorted {
    public static boolean isSorted(int[] array) {
         return IntStream.range(0, array.length - 1)
                .noneMatch(m -> array[m] >= array[m + 1]);
    }
}
