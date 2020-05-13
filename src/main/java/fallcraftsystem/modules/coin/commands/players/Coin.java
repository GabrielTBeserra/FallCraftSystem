package fallcraftsystem.modules.coin.commands.players;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.modules.coin.database.CoinData;
import fallcraftsystem.utils.PluginInfo;
import fallcraftsystem.utils.Ultilities;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.SQLException;

public class Coin implements CommandExecutor {
    public FallCraftSystem plugin;

    public Coin(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("coin").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (args.length == 0 && !(sender instanceof Player)) {
            sender.sendMessage(Ultilities.formater("&cUse /coin user"));
            return true;
        }


        if (args.length == 0) {
            Player player = (Player) sender;
            try {
                sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&6Seus coins: &2¢" + CoinData.getCoins(player)));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aCoin Help"));
            //sender.sendMessage(Ultilities.formater("&9/coin pay PLAYER &0- &6Tranferir coins para outro player"));
            sender.sendMessage(Ultilities.formater("&9/coin &0- &6Mostrar quantidade de coins"));
        } else if (args[0].equalsIgnoreCase("add") && sender.hasPermission("fallcraft.coin.admin.add")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    Player p = Bukkit.getPlayer(args[1]);
                    double amount = Double.parseDouble(args[2]);
                    CoinData.addCoins(p, amount);
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aAdicionado ¢" + amount + " coins para o player " + p.getName()));
                    p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&aRecebidos ¢" + amount + " coins"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cUse o comando /coin add <Nick> <Quantidade>"));
                }
            }
        } else if (args[0].equalsIgnoreCase("take") && sender.hasPermission("fallcraft.coin.admin.add")) {
            if (Bukkit.getPlayer(args[1]) != null) {
                try {
                    Player p = Bukkit.getPlayer(args[1]);
                    double amount = Double.parseDouble(args[2]);
                    if (CoinData.getCoins(p) < amount) {
                        sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cO player " + p.getName() + " nao possui a quantidade de ¢" + amount + " coins"));
                        return true;
                    }
                    CoinData.takeCoins(p, amount);
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cRemovido ¢" + amount + " coins do player " + p.getName()));
                    p.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cRetirados ¢" + amount + " coins"));
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cUse o comando /coin add <Nick> <Quantidade>"));
                }
            }
        } else if (sender.hasPermission("fallcraft.coin.admin") && Bukkit.getPlayer(args[0]) != null) {
            Player p = Bukkit.getPlayer(args[0]);
            try {
                sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&6Coins do player " + p.getName() + ": &2¢" + CoinData.getCoins(p)));
            } catch (SQLException throwables) {
                sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cO player pode nao estar online ou nao existir!"));
            }
        } else if (sender.hasPermission("fallcraft.coin.admin") && Bukkit.getPlayer(args[0]) != null) {
            sender.sendMessage(Ultilities.formater(PluginInfo.SERVER_NAME + "&cO player pode nao estar online ou nao existir!"));
        }


        return true;
    }
}
