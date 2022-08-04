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

    private static final ProjectRepository<Item> instance = new ItemRepository();
    private final Logger logger = Logger.getLogger(getClass().getName());
    private final DatabaseConnection dbConnection = new DatabaseConnection();

    public static ProjectRepository<Item> getInstance() {
        return instance;
    }

    private Connection getConnection() {
        try {
            return dbConnection.getConnection();
        } catch (SQLException | ClassNotFoundException | IOException e) {
            logger.severe(e.getMessage());
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
            logger.severe(e.toString());
            throw new RuntimeException(e);
        }

        return items;
    }

    @Override
    public boolean add(Item item) {
        try (PreparedStatement statement = getConnection()
                     .prepareStatement("INSERT INTO items VALUES(?, ?, ?)")) {
            statement.setInt(1, item.getCode());
            statement.setString(2, item.getName());
            statement.setInt(3, item.getPrice());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.warning(e.toString());
            return false;
        }
    }

    @Override
    public boolean removeById(int code) {
        try (PreparedStatement statement = getConnection().prepareStatement("DELETE FROM items WHERE code=" + code)) {
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.warning(e.toString());
            return false;
        }
    }

    @Override
    public boolean editById(int oldCode, Item newItem) {
        try (PreparedStatement statement = getConnection()
                     .prepareStatement("UPDATE items SET code=?, name=?, price=? WHERE code=" + oldCode)) {
            statement.setInt(1, newItem.getCode());
            statement.setString(2, newItem.getName());
            statement.setInt(3, newItem.getPrice());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            logger.warning(e.toString());
            return false;
        }
    }
}