package fallcraftsystem.modules.coin.database;

import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CoinData {

    public static double getCoins(Player player) throws SQLException {
        double ret = 0;
        String query = "select * from coins where uuid = ?";

        PreparedStatement sets = ConnectionFactory.getConnection().prepareStatement(query);
        sets.setString(1, player.getUniqueId().toString());

        ResultSet results = sets.executeQuery();


        while (results.next()) {
            ret = results.getDouble("amount");
        }

        return ret;
    }

    public static void insert(Player player) throws SQLException {
        String newPlayer = "insert into coins (uuid, amount) values (? , ?)";

        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);
        preparedStatement.setString(1, player.getUniqueId().toString());
        preparedStatement.setDouble(2, 0);
        preparedStatement.execute();
    }

    public static void addCoins(Player player, double amount) throws SQLException {
        String newPlayer = "update coins set amount = amount + " + amount + " where uuid = '" + player.getUniqueId().toString() + "'";


        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);

        preparedStatement.executeUpdate();
    }

    public static void takeCoins(Player player, double amount) throws SQLException {
        String newPlayer = "update coins set amount = amount - " + amount + " where uuid = '" + player.getUniqueId().toString() + "'";

        PreparedStatement preparedStatement = ConnectionFactory.getConnection().prepareStatement(newPlayer);

        preparedStatement.execute();
    }
}
