package fallcraftsystem.modules.luz.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.LuzStatus;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Luz implements CommandExecutor {
    public FallCraftSystem plugin;

    public Luz(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("luz").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command for only players!");
            return true;
        }
        Player player = (Player) sender;

        if (args.length == 1) {
            if (player.hasPermission("fallcraft.module.essentials.luz.admin")) {
                if (Bukkit.getPlayer(args[0]) != null) {
                    player = Bukkit.getPlayer(args[0]);
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aModo de Luz alterado para o player " + player.getName()));
                } else {
                    sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cO player nao esta online"));
                }
            } else {
                sender.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cYou don`t have permission for this!"));
                return true;
            }

        }


        GamePlayer gm = ServerUtils.players.get(player);

        if (gm.getLuzStatus().equals(LuzStatus.OFF)) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&aLuz ligada"));
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 18000 * 120, 254, false, false));

            ServerUtils.players.get(player).setLuzStatus(LuzStatus.ON);
        } else {

            player.removePotionEffect(PotionEffectType.NIGHT_VISION);

            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cLuz desligada"));
            ServerUtils.players.get(player).setLuzStatus(LuzStatus.OFF);
        }
        return true;
    }
}
