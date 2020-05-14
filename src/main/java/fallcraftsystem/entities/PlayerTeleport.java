package fallcraftsystem.entities;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerTeleport {
    private Player player;
    private int time;
    private Location location;
    private Location toLoc;

    public PlayerTeleport(int time, Location location, Location toLoc) {
        this.time = time;
        this.location = location;
        this.toLoc = toLoc;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getToLoc() {
        return toLoc;
    }

    public void setToLoc(Location toLoc) {
        this.toLoc = toLoc;
    }
}
