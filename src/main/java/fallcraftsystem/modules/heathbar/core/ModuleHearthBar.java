package fallcraftsystem.modules.heathbar.core;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.heathbar.listener.AddHearthBarToPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ModuleHearthBar {
    public FallCraftSystem plugin;
    private Scoreboard scoreboard;

    public ModuleHearthBar(FallCraftSystem plugin) {
        this.plugin = plugin;
        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        registerHealthBar();
        registerNameTag();
        new AddHearthBarToPlayer(plugin, scoreboard);
    }

    public void registerHealthBar() {
        if (scoreboard.getObjective("health") != null) {
            scoreboard.getObjective("health").unregister();
        }

        Objective o = scoreboard.registerNewObjective("health", "health");
        o.setDisplayName("❤");
        o.setDisplaySlot(DisplaySlot.BELOW_NAME);
    }

    public void registerNameTag() {
        if (scoreboard.getTeam("blue") != null) {
            scoreboard.getTeam("blue").unregister();
        }
        Team t = scoreboard.registerNewTeam("blue");
        t.setPrefix(ChatColor.BLUE + "");
    }
}
