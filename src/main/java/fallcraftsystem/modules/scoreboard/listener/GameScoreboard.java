package fallcraftsystem.modules.scoreboard.listener;

import com.massivecraft.factions.entity.MPlayer;
import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.dependencies.ChatVault;
import fallcraftsystem.utils.dependencies.VaultEconomy;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

public class GameScoreboard implements Listener {
    public FallCraftSystem plugin;

    public GameScoreboard(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onJoinPlayer(PlayerJoinEvent event) {
        Player player = event.getPlayer();


        new BukkitRunnable() {

            @Override
            public void run() {
                try {
                    ScoreboardManager scoreboardManager = plugin.getServer().getScoreboardManager();
                    Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
                    Objective objective = scoreboard.registerNewObjective(MethodsStatics.formater("&3&l" + player.getName()), "MENU");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);

                    MPlayer mplayer = MPlayer.get(player);


                    String facName = "";


                    Score score1 = objective.getScore(MethodsStatics.formater("&a Facção: &eSem facção"));


                    if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
                        score1 = objective.getScore(MethodsStatics.formater("&a Facção: &e" + mplayer.getFactionName()));
                    }


                    Score score2 = objective.getScore(MethodsStatics.formater("&a Power: &e" + String.format("%.1f", mplayer.getPower())));
                    Score score3 = objective.getScore(" ");
                    Score score4 = objective.getScore(MethodsStatics.formater("&a Money: &e" + VaultEconomy.getVault().getBalance(player)));
                    Score score5 = objective.getScore("  ");
                    Score score7 = objective.getScore(MethodsStatics.formater("&a PVP: " + MethodsStatics.formater("&6" + PluginInfo.players.get(player).getPvpStatus().toString())));
                    Score score8 = objective.getScore(MethodsStatics.formater("&a Players Online: &e" + Bukkit.getOnlinePlayers().size()));

                    score1.setScore(10);
                    score2.setScore(9);
                    score3.setScore(8);
                    score4.setScore(7);
                    score5.setScore(6);
                    score7.setScore(5);
                    score8.setScore(4);


                    if (!(mplayer.getFactionTag().equals("") || mplayer.getFactionTag().equals(" "))) {
                        facName = "&5[&f" + mplayer.getFactionTag() + "&5] ";
                    }


                    player.setPlayerListName(
                            MethodsStatics.formater(
                                    ChatVault.getChat().getPlayerPrefix(player) + facName + "&7" + player.getName()));


                    player.setScoreboard(scoreboard);
                } catch (Exception e) {
                    System.err.println("Player is null, canceling the timer");
                    this.cancel();
                }


            }
        }.runTaskTimer(plugin, 0, 10);

    }
}

