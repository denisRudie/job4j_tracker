package ru.job4j.tracker.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapConverter {
    private Integer[][] matrix = {
            {1, 2},
            {3, 4},
            {5, 6},
            {}
    };

    List<Integer> convert(Integer[][] matrix) {
        return Stream.of(matrix)
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        FlatMapConverter fmc = new FlatMapConverter();

        List<Integer> rsl = fmc.convert(fmc.matrix);

        System.out.println(rsl);
    }
}
