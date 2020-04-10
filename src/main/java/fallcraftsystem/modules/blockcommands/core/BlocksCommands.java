package fallcraftsystem.modules.blockcommands.core;

import java.util.ArrayList;
import java.util.List;

public class BlocksCommands {
    public static List<String> blockedCommands;

    public static void blockLoad() {
        BlocksCommands.blockedCommands = new ArrayList<String>();
        BlocksCommands.blockedCommands.add("/bukkit:plugins");
        BlocksCommands.blockedCommands.add("/bukkit:pl");
        BlocksCommands.blockedCommands.add("/plugins");
        // BlocksCommands.blockedCommands.add("/pl");
        BlocksCommands.blockedCommands.add("/me");
        BlocksCommands.blockedCommands.add("/worldedit:/calc");
        BlocksCommands.blockedCommands.add("/worldedit:/calculate");
        BlocksCommands.blockedCommands.add("/worldedit://calc");
        BlocksCommands.blockedCommands.add("/worldedit://calculate");
        BlocksCommands.blockedCommands.add("//worldedit:/calc");
        BlocksCommands.blockedCommands.add("//worldedit:/calculate");
        BlocksCommands.blockedCommands.add("//calc");
        BlocksCommands.blockedCommands.add("//calculate");
        BlocksCommands.blockedCommands.add("/minecraft:me");
        BlocksCommands.blockedCommands.add("/say");
        BlocksCommands.blockedCommands.add("/reload");
        BlocksCommands.blockedCommands.add("/minecraft:say");
    }
}
