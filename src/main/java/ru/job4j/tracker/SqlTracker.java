package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTracker implements Store {
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class);
    private Connection conn;

    public SqlTracker(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void init() {
        String url = "jdbc:postgresql://localhost:5432/tracker";
        String username = "postgres";
        String password = "123";
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement ps = conn.prepareStatement(
                "insert into items (name) values (?)",
                Statement.RETURN_GENERATED_KEYS)){
            ps.setString(1, item.getName());
            ps.executeUpdate();

            try (ResultSet generatedKey = ps.getGeneratedKeys()) {
                if (generatedKey.next()) {
                    item.setId(generatedKey.getInt(1));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        boolean rsl = false;
        try ( PreparedStatement st = conn.prepareStatement(
                "update items set name = ? where id = ?")){
            st.setString(1, item.getName());
            st.setInt(2, Integer.parseInt(id));

            if (st.executeUpdate() != 0) {
                rsl = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public boolean delete(String id) {
        boolean rsl = false;
        try (PreparedStatement st = conn.prepareStatement(
                "delete from items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));

            if (st.executeUpdate() != 0) {
                rsl = true;
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> itemList = new ArrayList<>();
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                Item i = new Item(rs.getString("name"));
                i.setId(rs.getInt("id"));
                itemList.add(i);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return itemList;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> itemList = new ArrayList<>();
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM items where name = ?")) {
            st.setString(1, key);

            try (ResultSet rs = st.executeQuery()) {
                while (rs.next()) {
                    Item i = new Item(rs.getString("name"));
                    i.setId(rs.getInt("id"));
                    itemList.add(i);
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return itemList;
    }

    @Override
    public Item findById(String id) {
        Item rsl = null;
        try (PreparedStatement st = conn.prepareStatement(
                "SELECT * FROM items where id = ?")) {
            st.setInt(1, Integer.parseInt(id));

            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    rsl = new Item(rs.getString("name"));
                    rsl.setId(rs.getInt("id"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return rsl;
    }

    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }
}

