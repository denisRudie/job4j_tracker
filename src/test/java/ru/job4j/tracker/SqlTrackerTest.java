package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class SqlTrackerTest {

    @Test
    public void whenDeleteThenTryToFind() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item i = tracker.add(new Item("item"));
        tracker.delete(String.valueOf(i.getId()));
        assertThat(tracker.findById(String.valueOf(i.getId())), nullValue());
    }

    @Test
    public void whenAddThenTryToFindById() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        Item addedItem = tracker.add(new Item("item"));
        Item foundedItem = tracker.findById(String.valueOf(addedItem.getId()));
        assertThat(foundedItem, is(addedItem));
    }

    @Test
    public void whenAdd2ItemsThenTryToFindByName() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        UUID uuid = UUID.randomUUID();
        Item i1 = tracker.add(new Item(uuid.toString()));
        Item i2 = tracker.add(new Item(uuid.toString()));
        List<Item> iList = tracker.findByName(uuid.toString());
        assertThat(iList.get(0), is(i1));
        assertThat(iList.get(1), is(i2));
    }

    @Test
    public void whenAddItemThenUpdateAndGet() {
        SqlTracker tracker = new SqlTracker();
        tracker.init();
        UUID uuid = UUID.randomUUID();
        Item addedItem = tracker.add(new Item(uuid.toString()));
        tracker.replace(String.valueOf(addedItem.getId()), new Item("updatedItem"));
        Item foundedItem = tracker.findById(String.valueOf(addedItem.getId()));
        assertThat(foundedItem.getName(), is("updatedItem"));
    }
}