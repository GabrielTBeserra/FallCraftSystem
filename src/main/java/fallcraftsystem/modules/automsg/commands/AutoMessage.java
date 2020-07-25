package fallcraftsystem.modules.automsg.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.automsg.utils.Messages;
import fallcraftsystem.modules.automsg.utils.MessagesConfig;
import fallcraftsystem.modules.automsg.utils.MessagesMap;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashMap;

public class AutoMessage implements CommandExecutor {
    public FallCraftSystem plugin;

    public AutoMessage(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("automessage").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("<ajuda>");
        }

        int quantidade = MessagesConfig.getMessagesFile().getInt("quantidade");

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("pause")) {

                for (int i = 1; i <= quantidade; i ++) {
                    MessagesMap.getMessages().get(i).pause();
                }

                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cAutoMessages pausado!"));
            }

            if (args[0].equalsIgnoreCase("resume")) {

                for (int i = 1; i <= quantidade; i ++) {
                    MessagesMap.getMessages().get(i).resume();
                }

                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aAutoMessages despausado!"));
            }

            if (args[0].equalsIgnoreCase("list")) {

                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "§3Lista de mensagens:"));

                for (int i = 1; i <= quantidade; i ++) {

                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&9" + i + "- &r"
                            + MessagesMap.getMessages().get(i).getMessage()));

                    sender.sendMessage(Ultilities.formater("                     &eTempo: &c"
                            + MessagesMap.getMessages().get(i).getSeconds() + "&c segundos&e."));

                    sender.sendMessage(Ultilities.formater("                     &eDelay: &c"
                            + MessagesMap.getMessages().get(i).getDelay() + "&c segundos&e."));

                }
            }
        }

        return true;
    }
}
