package fallcraftsystem.modules.automsg.events;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.automsg.utils.Messages;
import fallcraftsystem.modules.automsg.utils.MessagesConfig;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.HashMap;

import static fallcraftsystem.modules.automsg.utils.MessagesMap.*;

public class PlayerJoin implements Listener {
    public FallCraftSystem plugin;

    public PlayerJoin(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void playerJoinEvent(PlayerJoinEvent event){
        int quantOn = plugin.getServer().getOnlinePlayers().toArray().length;
        if (quantOn == 1) {

            int quantidade = MessagesConfig.getMessagesFile().getInt("quantidade");

            HashMap<Integer, Messages> messagesHashMap = getMessages();
            Messages message;
            if (messagesHashMap != null) {
                for (int i = 1; i <= quantidade; i++) {
                    message = messagesHashMap.get(i);
                    message.resume();
                }
            }

        }
    }

    @EventHandler
    public void playerQuitEvent(PlayerQuitEvent event){
        int quantOn = plugin.getServer().getOnlinePlayers().toArray().length;
        if (quantOn == 1) {

            int quantidade = MessagesConfig.getMessagesFile().getInt("quantidade");

            HashMap<Integer, Messages> messagesHashMap = getMessages();
            Messages message;

            for (int i = 1; i <= quantidade; i++) {
                message = messagesHashMap.get(i);
                message.pause();
            }
        }
    }
}
