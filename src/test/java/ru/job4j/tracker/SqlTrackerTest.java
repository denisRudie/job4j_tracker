package ru.job4j.tracker;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class SqlTrackerTest {
    private static final Logger LOG = LoggerFactory.getLogger(SqlTrackerTest.class);

    public Connection init() {
        String url = "jdbc:postgresql://localhost:5432/tracker";
        String username = "postgres";
        String password = "123";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return conn;
    }

    @Test
    public void whenAddThenFindByName() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name = UUID.randomUUID().toString();
            tracker.add(new Item(name));
            assertThat(tracker.findByName(name).size(), is(1));
        }
    }

    @Test
    public void whenAddThenTryToFindById() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name = UUID.randomUUID().toString();
            Item addedItem = tracker.add(new Item(name));
            Item foundedItem = tracker.findById(String.valueOf(addedItem.getId()));
            assertThat(foundedItem, is(addedItem));
        }
    }

    @Test
    public void whenDeleteThenTryToFind() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name = UUID.randomUUID().toString();
            Item i = tracker.add(new Item(name));
            tracker.delete(String.valueOf(i.getId()));
            assertThat(tracker.findById(String.valueOf(i.getId())), nullValue());
        }
    }

    @Test
    public void whenAdd2SameItemsThenFindByName() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name = UUID.randomUUID().toString();
            Item i1 = tracker.add(new Item(name));
            Item i2 = tracker.add(new Item(name));
            List<Item> iList = tracker.findByName(name);
            assertThat(iList.size(), is(2));
        }
    }

    @Test
    public void whenAddItemThenUpdateThenGet() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name = UUID.randomUUID().toString();
            String updatedName = UUID.randomUUID().toString();
            Item addedItem = tracker.add(new Item(name));
            tracker.replace(String.valueOf(addedItem.getId()), new Item(updatedName));
            Item foundedItem = tracker.findById(String.valueOf(addedItem.getId()));
            assertThat(foundedItem.getName(), is(updatedName));
        }
    }

    @Test
    public void whenAdd2ItemsThenFindAllItems() throws SQLException {
        try (SqlTracker tracker = new SqlTracker(ConnectionRollback.create(this.init()))) {
            String name1 = UUID.randomUUID().toString();
            String name2 = UUID.randomUUID().toString();
            tracker.add(new Item(name1));
            tracker.add(new Item(name2));
            List<Item> items = tracker.findAll();
            assertTrue(items.size() >= 2);
        }
    }
}