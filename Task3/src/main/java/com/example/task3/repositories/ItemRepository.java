package com.example.task3.repositories;

import com.example.task3.db.DatabaseConnection;
import com.example.task3.dto.Item;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class ItemRepository implements ProjectRepository<Item> {

    private static final ItemRepository instance = new ItemRepository();

    public static ItemRepository getInstance() {
        return instance;
    }

    private Connection getConnection() {
        try {
            return DatabaseConnection.initializeDatabase();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            Logger.getGlobal().severe(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> retrieveAll() {
        List<Item> items = new ArrayList<>();

        try (Connection connection = getConnection();
             ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM items")) {
            while (rs.next()) {
                items.add(new Item(rs.getInt("code"), rs.getString("name"), rs.getInt("price")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return items;
    }

    @Override
    public boolean add(Item item) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO items VALUES(?, ?, ?)")) {
            statement.setInt(1, item.getCode());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getGlobal().warning(e.getSQLState());
            return false;
        }
    }

    @Override
    public boolean remove(Item item) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("DELETE FROM items WHERE code=" + item.getCode())) {
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getGlobal().warning(e.getSQLState());
            return false;
        }
    }

    @Override
    public boolean edit(Item oldItem, Item newItem) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE items SET code=?, name=?, price=? WHERE code=" + oldItem.getCode())) {
            statement.setInt(1, newItem.getCode());
            statement.setString(2, newItem.getName());
            statement.setInt(3, newItem.getPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            Logger.getGlobal().warning(e.getSQLState());
            return false;
        }
    }
}