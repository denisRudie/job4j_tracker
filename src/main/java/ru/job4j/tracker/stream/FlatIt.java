package ru.job4j.tracker.stream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class FlatIt {
    public static List<Integer> flatten(Iterator<Iterator<Integer>> it) {
        ArrayList<Integer> list = new ArrayList<>();

        Stream.generate(() -> null)
                .takeWhile(i -> it.hasNext())
                .map(n -> it.next())
                .forEach(m -> m.forEachRemaining(list::add));

        return list;
    }
}