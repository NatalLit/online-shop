package dao;

import config.PropertiesReader;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class ConnectionManager {

    private static final PGSimpleDataSource postgresqlSource = new PGSimpleDataSource();

    private static final String SERVER_KEY = "db.server";
    private static final String PORT_KEY = "db.port";
    private static final String NAME_KEY = "db.name";
    public static final String USERNAME_KEY = "db.username";
    public static final String PASSWORD_KEY = "db.password";
    public static final String URL_KEY = "db.url";


//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(
//                    PropertiesReader.get(URL_KEY),
//                    PropertiesReader.get(USERNAME_KEY),
//                    PropertiesReader.get(PASSWORD_KEY)
//            );
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }


    public static Connection getConnection() {

        postgresqlSource.setServerNames(PropertiesReader.getStringArray(SERVER_KEY));
        postgresqlSource.setPortNumbers(PropertiesReader.getIntArray(PORT_KEY));
        postgresqlSource.setUser(PropertiesReader.get(USERNAME_KEY));
        postgresqlSource.setPassword(PropertiesReader.get(PASSWORD_KEY));
        postgresqlSource.setDatabaseName(PropertiesReader.get(NAME_KEY));
        try {
            return postgresqlSource.getConnection();
        } catch (SQLException ex) {
            throw new RuntimeException("Connection failed", ex);
        }
    }


}
