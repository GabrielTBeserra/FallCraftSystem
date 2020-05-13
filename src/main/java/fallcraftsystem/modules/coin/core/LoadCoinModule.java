package fallcraftsystem.modules.coin.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.commands.players.Coin;
import fallcraftsystem.modules.coin.database.LoadTables;
import fallcraftsystem.modules.coin.listener.AddPlayer;

import java.sql.SQLException;

public class LoadCoinModule {
    public LoadCoinModule(FallCraftSystem pl) {
        try {
            LoadTables.load();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        new Coin(pl);
        new AddPlayer(pl);

    }

}
