package fallcraftsystem.modules.coin.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class AddPlayer implements Listener {
    public FallCraftSystem pl;

    public AddPlayer(FallCraftSystem pl) {
        this.pl = pl;
        pl.getServer().getPluginManager().registerEvents(this, pl);
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent event) {
        try {
            CoinData.insert(event.getPlayer());
        } catch (SQLException throwables) {

        }
    }
}
