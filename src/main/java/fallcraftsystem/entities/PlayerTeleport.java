package fallcraftsystem.entities;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerTeleport {
    private Player player;
    private int time;
    private Location location;
    private Location toLoc;
    private int invincibility;
    private boolean teleported;


    public PlayerTeleport(int time, Location location, Location toLoc) {
        this.time = time;
        this.location = location;
        this.toLoc = toLoc;
        this.invincibility = -1;
        this.teleported = false;
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

    public int getInvincibility() {
        return invincibility;
    }

    public boolean isTeleported() {
        return teleported;
    }

    public void setTeleported(boolean teleported) {
        this.teleported = teleported;
    }

    public void setInvincibility(int invincibility) {
        this.invincibility = invincibility;
    }
}
