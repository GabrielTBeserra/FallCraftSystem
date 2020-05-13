package fallcraftsystem.modules.coin.database;

import fallcraftsystem.core.FallCraftSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static Connection connection = null;
    private final FallCraftSystem pl = FallCraftSystem.plugin;

    private ConnectionFactory() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:" + pl.getDataFolder() + "/coin.db");
    }

    public static Connection getConnection() {

        if (connection == null) {
            try {
                new ConnectionFactory();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return connection;
    }
}
