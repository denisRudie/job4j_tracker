package ru.job4j.tracker.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class FlatMapConverterTest {

    private FlatMapConverter fmc = new FlatMapConverter();

    private Integer[][] matrix = {
            {1, 2},
            {3, 4},
            {5, 6},
            {}
    };

    private List<Integer> model = List.of(1, 2, 3, 4, 5, 6);

    @Test
    public void ListContainsArray() {
        List<Integer> rsl = fmc.convert(matrix);
        Assert.assertThat(rsl, is(model));
    }
}
