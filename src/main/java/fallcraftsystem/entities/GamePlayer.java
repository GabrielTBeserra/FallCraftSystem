package fallcraftsystem.entities;

import fallcraftsystem.entities.enums.FlyStatus;
import fallcraftsystem.entities.enums.PlayerStatus;
import fallcraftsystem.entities.enums.PvpStatus;
import fallcraftsystem.entities.enums.VanishStatus;
import org.bukkit.entity.Player;

public class GamePlayer {
    private Player player;
    private PlayerStatus playerStatus;
    private VanishStatus vanishStatus;
    private FlyStatus fLyStatus;
    private PvpStatus pvpStatus;

    public GamePlayer(Player player, PlayerStatus playerStatus, VanishStatus vanishStatus, FlyStatus fLyStatus, PvpStatus pvpStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
        this.vanishStatus = vanishStatus;
        this.fLyStatus = fLyStatus;
        this.pvpStatus = pvpStatus;
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

    public FlyStatus getfLyStatus() {
        return fLyStatus;
    }

    public void setfLyStatus(FlyStatus fLyStatus) {
        this.fLyStatus = fLyStatus;
    }

    public PvpStatus getPvpStatus() {
        return pvpStatus;
    }

    public void setPvpStatus(PvpStatus pvpStatus) {
        this.pvpStatus = pvpStatus;
    }
}
