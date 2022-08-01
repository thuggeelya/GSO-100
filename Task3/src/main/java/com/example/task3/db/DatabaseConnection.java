package com.example.task3.db;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DatabaseConnection {

    public static Connection initializeDatabase() throws SQLException, ClassNotFoundException, IOException {
        Properties properties = getProperties();
        String dbDriver =   properties.getProperty("database.driver");
        String dbURL =      properties.getProperty("database.url");
        String dbName =     properties.getProperty("database.name");
        String dbUser =     properties.getProperty("database.user");
        String dbPassword = properties.getProperty("database.password");
        Class.forName(dbDriver);
        Logger.getGlobal().info("connecting to " + dbURL);
        return DriverManager.getConnection(dbURL + dbName,
                dbUser,
                dbPassword);
    }

    private static Properties getProperties() throws IOException {
        URL resource = DatabaseConnection.class.getClassLoader().getResource("database.properties");

        if (resource == null) {
            throw new IOException("Unable get resource with name: database.properties");
        }

        File file = new File(resource.getFile());

        try (InputStream input = Files.newInputStream(file.toPath())) {
            Properties prop = new Properties();
            prop.load(input);
            return prop;
        }
    }
}