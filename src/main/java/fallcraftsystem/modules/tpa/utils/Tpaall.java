package fallcraftsystem.modules.tpa.utils;

import fallcraftsystem.utils.ServerUtils;
import fallcraftsystem.utils.Ultilities;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class Tpaall {
    Player player;
    Player admin;


    public Tpaall(Player player, Player admin) {
        this.player = player;
        this.admin = admin;
        tpahere(player, admin);
    }

    public void tpahere(Player player, Player admin) {
        player.sendMessage(Ultilities.formater(ServerUtils.SERVER_NAME + "&6O staff &c" + admin.getName()
                + "&6 está te convidando para o evento!"));

        Utilities.getTpaHere().put(player, admin);

        TextComponent txt = new TextComponent();
        TextComponent txt2 = new TextComponent();

        txt.setText("§c§nClique aqui.");
        txt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("Clique para aceitar!").create()));
        txt.setClickEvent(new ClickEvent(net.md_5.bungee.api.chat.ClickEvent.Action.RUN_COMMAND, "/tpaccept"));

        txt2.setText(Ultilities.formater(ServerUtils.SERVER_NAME + "&6Para aceitar "));

        player.spigot().sendMessage(txt2, txt);



    }
}
