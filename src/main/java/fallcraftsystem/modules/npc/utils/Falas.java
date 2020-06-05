package fallcraftsystem.modules.npc.utils;

import fallcraftsystem.utils.Ultilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

public class Falas {
    private static ItemStack getFalasZephyr(Player player) {
        List<String> falasZephyr = new ArrayList<>();
        falasZephyr.add(Ultilities.formater("&9Olá %user%, seja bem vindo ao &bFallCraft &9Factions."
                + "&9Estou aqui para te ajudar a começar sua aventura e deixar tudo claro para que você não se perca na escuridão desse mundo.")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater(
                "&9Antes de começar a explicar os detalhes, irei lhe dar um contexto do mundo que vocês está prestes a entrar./n/n")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater(
                "&9Aqui no FallCraft, as facções lutam bravamente para conseguirem conquistar o título de &c'Facção de Elite' &9que só vai para aquela que é\n"
                        + "campeã da temporada do maior evento competitivo entre facções já visto.")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater(
                "&9Esse é o evento em que o FallCraft gira em torno, porém, no que nós chamamos de &c'off-season', &9que são os intervalos de tempo entre uma\n"
                        + "batalha e outra, as facções lutam para enfraquecerem umas as outras com &cinvasões")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater(
                "&9conflitos abertos, e muito PVP. &9Durante o off-season, há varios eventos em volta do mundo que podem render muitos benefícios a quem os enfrentar.\n"
                        + "Esse acontecimentos envolvem &c'Supply Drops', 'Boss Fights', Dungeons, etc.")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater(
                "&9Agora que você já sabe como funciona o mundo do FallCraft, podemos seguir para os detalhes de como criar sua facção e começar sua\n"
                        + "jornada.")
                .replace("%user%", player.getName()));
        falasZephyr.add(Ultilities.formater("&9Fale com o &6Ares &9ao lado para continuar o tutorial.")
                .replace("%user%", player.getName()));

        ItemStack zepyhrBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) zepyhrBook.getItemMeta();
        bookMeta.setTitle("Zephyr");
        bookMeta.setAuthor("Zephyr");
        bookMeta.setPages(falasZephyr);
        zepyhrBook.setItemMeta(bookMeta);

        return zepyhrBook;
    }

    private static ItemStack getFalasAres(Player player) {
        List<String> falasAres = new ArrayList<>();
        falasAres.add(Ultilities.formater("&9Existem duas maneiras principais de criar uma Facção:.").replace("%user%",
                player.getName()));
        falasAres.add(Ultilities.formater(
                "&b1- Pelo &dmenu de Facções&b (acessado digitando '&d/f&b'  no chat) selecionando o &8banner branco&b. Após clicar na opção, serão perguntado no chat a &3tag &be o &3nome&b.")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater(
                "&b2- Digitando no chat &d/f criar <&3tag&d> <&3nome&d>&b. Por exemplo: &d/f criar &3LDD LadyDriade.")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater("&9Para desfazer uma facção é mais fácil ainda:").replace("%user%",
                player.getName()));
        falasAres.add(Ultilities.formater("- &bNo &dMenu de Facções&b, basta clicar na opção com uma &7porta.")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater(
                "&9Agora que você sabe como criar sua facção, fale com a &9&lDriade &9para saber como começar sua jornada.")
                .replace("%user%", player.getName()));

        ItemStack aresBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) aresBook.getItemMeta();
        bookMeta.setTitle("Zephyr");
        bookMeta.setAuthor("Zephyr");
        bookMeta.setPages(falasAres);
        aresBook.setItemMeta(bookMeta);

        return aresBook;
    }

    private static ItemStack getFalasDriade(Player player) {
        List<String> falasAres = new ArrayList<>();
        falasAres.add(Ultilities.formater(
                "&9Vejo que você já sabe tudo sobre o que te aguarda no FallCraft. Para começar sua jornada, basta voltar ao centro do nosso Spawn, e seguir em direção a saida. Chegando lá você terá duas opções:")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater(
                "&91. Usar os portais de água para ser teletransportado para um lugar aleatorio e começar sua aventura")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater("&92. Pular em direção ao &cparedão.").replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater(
                "&9O paredão é um lugar temido por todos! Há muitas armadilhas e obstáculos que podem dificultar sua saída para o mundo.")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater(
                "&9É isso %user%! Você está pronto. Lembre, caso ainda haja dúvidas, faça contato com nossos criadores através do &c/discord &9ou do &c/ajudastaff")
                .replace("%user%", player.getName()));
        falasAres.add(Ultilities.formater("&9Boa sorte!").replace("%user%", player.getName()));

        ItemStack driadeBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) driadeBook.getItemMeta();
        bookMeta.setTitle("Zephyr");
        bookMeta.setAuthor("Zephyr");
        bookMeta.setPages(falasAres);
        driadeBook.setItemMeta(bookMeta);

        return driadeBook;
    }

    public static ItemStack getFala(Player player, int fala) {
        switch (fala) {
            case 1:
                return getFalasZephyr(player);
            case 2:
                return getFalasAres(player);
            case 3:
                return getFalasDriade(player);
        }

        return null;
    }
}
