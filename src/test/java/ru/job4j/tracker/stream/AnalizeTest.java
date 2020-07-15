package ru.job4j.tracker.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class AnalizeTest {
    @Test
    public void check() {
        Analize analize = new Analize();
        Analize.Info infoTest = new Analize.Info(1, 1, 0);
        List<Analize.User> previous = List.of(
                new Analize.User(1, "Mike"),
                new Analize.User(2, "Mike"),
                new Analize.User(3, "Mike")
        );

        List<Analize.User> current = List.of(
                new Analize.User(1, "Mike"),
                new Analize.User(2, "John"),
                new Analize.User(3, "Mike"),
                new Analize.User(4, "Mike")
        );

        Assert.assertThat(analize.diff(previous, current), is(infoTest));
    }
}
