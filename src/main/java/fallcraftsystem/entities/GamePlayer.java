package fallcraftsystem.entities;

import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.entities.enums.PlayerStatus;
import fallcraftsystem.entities.enums.VanishStatus;
import org.bukkit.entity.Player;

public class GamePlayer {
    private Player player;
    private PlayerStatus playerStatus;
    private VanishStatus vanishStatus;
    private FlyStatus fLyStatus;


    public GamePlayer(Player player, PlayerStatus playerStatus, VanishStatus vanishStatus, FlyStatus fLyStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
        this.vanishStatus = vanishStatus;
        this.fLyStatus = fLyStatus;
    }

    public FlyStatus getfLyStatus() {
        return fLyStatus;
    }

    public void setfLyStatus(FlyStatus fLyStatus) {
        this.fLyStatus = fLyStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public VanishStatus getVanishStatus() {
        return vanishStatus;
    }

    public void setVanishStatus(VanishStatus vanishStatus) {
        this.vanishStatus = vanishStatus;
    }
}
