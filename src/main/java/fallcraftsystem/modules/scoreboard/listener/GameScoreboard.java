package fallcraftsystem.modules.scoreboard.listener;

import com.massivecraft.factions.entity.MPlayer;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.entities.enums.InvStatus;
import fallcraftsystem.entities.enums.SpyStatus;
import fallcraftsystem.entities.enums.VanishStatus;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.sql.SQLException;

public class GameScoreboard implements Listener {
    public FallCraftSystem plugin;

    public GameScoreboard(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    private static void adminPlayerScoreboard(Player player) throws SQLException {
        ScoreboardManager scoreboardManager = FallCraftSystem.plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(Ultilities.formater("&5&l" + player.getName()), "MENU");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        MPlayer mplayer = MPlayer.get(player);


        String facName = "";

        Score score1 = objective.getScore(" ");
        Score score2 = objective.getScore(Ultilities.formater("&f Facção: &8Sem facção"));


        if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
            score2 = objective.getScore(Ultilities.formater("&f Facção: &e" + mplayer.getFactionName()));
            
        }


        Score score3 = objective.getScore(Ultilities.formater("&f Power: &c" + String.format("%.1f", mplayer.getPower())));
        Score score4 = objective.getScore("  ");
        Score score5 = objective.getScore(Ultilities.formater("&f Money: &a$" + VaultEconomy.getVault().getBalance(player)));
        Score score6 = objective.getScore(Ultilities.formater("&f Coins: &2¢" + String.format("%.1f", CoinData.getCoins(player))));
        Score score7 = objective.getScore("    ");
        Score score8 = objective.getScore(Ultilities.formater("&f PVP: " + Ultilities.formater("&6" + PluginInfo.players.get(player).getPvpStatus().toString())));
        Score score9 = objective.getScore("     ");
        Score score10 = objective.getScore(Ultilities.formater("&6 === STAFF === "));
        Score score11 = objective.getScore("       ");
        Score score12 = objective.getScore(Ultilities.formater("&f Invisibilidade: " + (PluginInfo.players.get(player).getInvStatus().equals(InvStatus.INVISIBLE) ? "&aON" : "&cOFF")));
        Score score13 = objective.getScore(Ultilities.formater("&f Vanish: &c" + (PluginInfo.players.get(player).getVanishStatus().equals(VanishStatus.INVISIBLE) ? "&aON" : "&cOFF")));
        Score score14 = objective.getScore(Ultilities.formater("&f Spy: &c" + (PluginInfo.players.get(player).getSpyStatus().equals(SpyStatus.ON) ? "&aON" : "&cOFF")));
        Score score15 = objective.getScore(Ultilities.formater("&f Fly: &c" + (PluginInfo.players.get(player).getFlyStatus().equals(FlyStatus.FLYING) ? "&aON" : "&cOFF")));
        Score score16 = objective.getScore(Ultilities.formater("                 "));


        score1.setScore(20);
        score2.setScore(19);
        score3.setScore(18);
        score4.setScore(17);
        score5.setScore(16);
        score6.setScore(15);
        score7.setScore(14);
        score8.setScore(13);
        score9.setScore(12);
        score10.setScore(11);
        score11.setScore(10);
        score12.setScore(9);
        score13.setScore(8);
        score14.setScore(7);
        score15.setScore(6);
        score16.setScore(5);


        if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
            facName = "&5[&f" + mplayer.getFactionTag() + "&5] ";
        }


        player.setPlayerListName(
                Ultilities.formater(
                        ChatVault.getChat().getPlayerPrefix(player) + facName + "&7" + player.getName()));
        player.setScoreboard(scoreboard);
    }


    private static void normalPlayerScoreboard(Player player) throws SQLException {

        ScoreboardManager scoreboardManager = FallCraftSystem.plugin.getServer().getScoreboardManager();
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective(Ultilities.formater("&5&l" + player.getName()), "MENU");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        MPlayer mplayer = MPlayer.get(player);


        String facName = "";

        Score score1 = objective.getScore(" ");
        Score score2 = objective.getScore(Ultilities.formater("&f Facção: &8Sem facção"));


        if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
            score2 = objective.getScore(Ultilities.formater("&f Facção: &e" + mplayer.getFactionName()));
        }


        Score score3 = objective.getScore(Ultilities.formater("&f Power: &c" + String.format("%.1f", mplayer.getPower())));
        Score score4 = objective.getScore("  ");
        Score score5 = objective.getScore(Ultilities.formater("&f Money: &a$" + VaultEconomy.getVault().getBalance(player)));
        Score score6 = objective.getScore(Ultilities.formater("&f Coins: &2¢" + String.format("%.1f", CoinData.getCoins(player))));
        Score score7 = objective.getScore("    ");
        Score score8 = objective.getScore(Ultilities.formater("&f PVP: " + Ultilities.formater("&6" + PluginInfo.players.get(player).getPvpStatus().toString())));
        Score score9 = objective.getScore("     ");

        score1.setScore(10);
        score2.setScore(9);
        score3.setScore(8);
        score4.setScore(7);
        score5.setScore(6);
        score6.setScore(5);
        score7.setScore(4);
        score8.setScore(3);
        score9.setScore(2);


        if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
            facName = "&5[&f" + mplayer.getFactionTag() + "&5] ";
        }


        player.setPlayerListName(
                Ultilities.formater(
                        ChatVault.getChat().getPlayerPrefix(player) + facName + "&7" + player.getName()));
        player.setScoreboard(scoreboard);
    }

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        new BukkitRunnable() {
            @Override
            public void run() {
                try {
                    if (player.hasPermission("admin.scoreboard")) {
                        adminPlayerScoreboard(player);
                    } else {
                        normalPlayerScoreboard(player);
                    }
                } catch (Exception e) {
                    System.err.println("Player is null, canceling the timer");
                    this.cancel();
                }
            }
        }.runTaskTimer(plugin, 0, 7);


    }

}

