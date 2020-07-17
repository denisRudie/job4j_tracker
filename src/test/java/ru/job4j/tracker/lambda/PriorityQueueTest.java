package ru.job4j.tracker.lambda;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PriorityQueueTest {
    @Test
    public void whenHigherPriority() {
        PriorityQueue queue = new PriorityQueue();
        queue.put(new Task("low", 5));
        queue.put(new Task("urgent", 1));
        queue.put(new Task("middle", 3));
        Task result1 = queue.take();
        assertThat(result1.getDesc(), is("urgent"));
        Task result2 = queue.take();
        assertThat(result2.getDesc(), is("middle"));
        Task result3 = queue.take();
        assertThat(result3.getDesc(), is("low"));
    }
}