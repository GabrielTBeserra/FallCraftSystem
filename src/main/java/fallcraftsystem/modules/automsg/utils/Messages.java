package fallcraftsystem.modules.automsg.utils;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Messages {
    int seconds;
    int delay;
    String message;
    boolean run;
    int index;

    public Messages(int seconds, String message, int delay) {
        this.seconds = seconds;
        this.message = message.replace("&", "§");
        this.index = index;
        this.delay = delay;
        this.run = false;
        autoMessage();
    }

    private void autoMessage() {
        new BukkitRunnable(){
            @Override
            public void run() {
                if(run) {
                    Bukkit.broadcastMessage(Ultilities.formater(ServerUtils.SERVER_NAME) + message);
                }
            }
        }.runTaskTimerAsynchronously(FallCraftSystem.plugin, 20 * delay, 20 * seconds);
    }

    public void pause() {
        run = false;
    }

    public void resume() {
        run = true;
    }

    public int getSeconds() {
        return seconds;
    }

    public int getDelay() {
        return delay;
    }

    public String getMessage() {
        return message;
    }

    public boolean isRun() {
        return run;
    }

    public int getIndex() {
        return index;
    }

}
