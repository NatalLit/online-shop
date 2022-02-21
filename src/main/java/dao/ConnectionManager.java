package dao;

import config.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";
    public static final String URL_KEY = "db.url";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PropertiesReader.get(URL_KEY),
                    PropertiesReader.get(USERNAME_KEY),
                    PropertiesReader.get(PASSWORD_KEY)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
