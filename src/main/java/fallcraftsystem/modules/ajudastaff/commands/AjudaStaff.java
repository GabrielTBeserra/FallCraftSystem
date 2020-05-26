package fallcraftsystem.modules.ajudastaff.commands;

import fallcraftsystem.core.FallCraftSystem;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import fallcraftsystem.utils.Ultilities;

public class AjudaStaff implements CommandExecutor {
    public FallCraftSystem plugin;

    public AjudaStaff(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("ajudastaff").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Comando apenas para players!");
            return true;
        }

        Player player = (Player) sender;

        if (args.length < 1) {
            player.sendMessage(Ultilities.formater(("§7Digite §a/audastaff <mensagem>")));
            return true;
        }

        String mensagem = "";

        for (String arg : args) {
            mensagem += arg + " ";
        }
        mensagem = Ultilities.formater("&6[&fAjuda&bStaff&6] &f" + player.getName() + "&f: &6" + mensagem + "");

        player.sendMessage(Ultilities.formater("&6[&fAjuda&bStaff&6] &fA staff recebeu a sua mensagem, se tiver alguem online te responderá assim que possível!"));
        Bukkit.broadcast(mensagem,
                "fallcraft.module.ajudastaff");
        Bukkit.getConsoleSender().sendMessage(mensagem);
        return true;
    }
}
