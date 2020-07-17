package ru.job4j.tracker.lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        var queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        var result1 = queue.take();
        assertThat(result1.getDesc(), is("urgent"));
        var result2 = queue.take();
        assertThat(result2.getDesc(), is("middle"));
        var result3 = queue.take();
        assertThat(result3.getDesc(), is("low"));
    }
}