package fallcraftsystem.modules.scoreboard.listener;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.MethodsStatics;
import fallcraftsystem.utils.PluginInfo;
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
                    Objective objective = scoreboard.registerNewObjective("STATS", "MENU");
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    Team health = scoreboard.registerNewTeam("health");
                    health.addEntry("Health: §b");
                    health.setSuffix("");
                    health.setPrefix("");
                    final Score score = objective.getScore(MethodsStatics.formater("&2&lPlayer: " + MethodsStatics.formater("&6" + player.getName())));
                    final Score score1 = objective.getScore(MethodsStatics.formater("&2&lPVP: " + MethodsStatics.formater("&6" + PluginInfo.players.get(player).getPvpStatus().toString())));
                    final Runtime r = Runtime.getRuntime();
                    final long memUsed = r.totalMemory() - r.freeMemory();

                    score.setScore(8);
                    score1.setScore(7);

                    player.setScoreboard(scoreboard);
                } catch (Exception e) {
                    System.err.println("Player is null, canceling the timer");
                    this.cancel();
                }


            }
        }.runTaskTimer(plugin, 0, 10);

    }
}

