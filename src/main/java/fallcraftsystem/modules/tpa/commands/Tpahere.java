package fallcraftsystem.modules.tpa.commands;

import fallcraftsystem.core.FallCraftSystem;
import fallcraftsystem.entities.GamePlayer;
import fallcraftsystem.entities.enums.TpaStatus;
import fallcraftsystem.modules.tpa.utils.Utilities;
import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpahere implements CommandExecutor {
    public FallCraftSystem plugin;

    public Tpahere(FallCraftSystem plugin) {
        this.plugin = plugin;
        plugin.getCommand("tpahere").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cComando apenas para players.");
            return true;
        }

        Player player = (Player) sender;


        if (args.length != 1) return false;

        Player player2 = Bukkit.getPlayer(args[0]);
        if (!(player2 != null && player2.isOnline())) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cJogador Offline. "));
            return true;
        }

        if (player.getName().equals(player2.getName())) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&cVocê nao pode mandar tpa para si."));
            return true;
        }

        GamePlayer gamePlayer = ServerUtils.players.get(player2);

        if (gamePlayer.getTpaStatus().equals(TpaStatus.OFF)) {
            player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&c " + player2.getName() + " desligou seus pedidos de teleporte. "));
            return true;
        }


        Utilities.getTpaHere().put(player2, player);

        TextComponent txt = new TextComponent();
        TextComponent txt2 = new TextComponent();

        txt.setText("§c/tpaccept§6.");
        txt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para aceitar!").create()));
        txt.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        txt2.setText(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Para aceitar digite (ou clique): "));

        TextComponent txt3 = new TextComponent();
        TextComponent txt4 = new TextComponent();

        txt3.setText("§c/tpdeny");
        txt3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para rejeitar!").create()));
        txt3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny"));

        txt4.setText(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Para rejeitar digite (ou clique): "));

        player2.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6O &c" + player.getName() + "&6 pediu para te teletransportar para lá."));
        player2.spigot().sendMessage(txt2, txt);
        player2.spigot().sendMessage(txt4, txt3);
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Pedido enviado para &c" + player2.getName() + "&6."));

        return true;
    }
}