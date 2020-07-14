package ru.job4j.tracker.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, -1, 0, -19, 2010));
        List<Integer> filteredList = list.stream()
                .filter(x -> x > 0)
                .collect(Collectors.toList());

        filteredList.forEach(System.out::println);
    }
}
