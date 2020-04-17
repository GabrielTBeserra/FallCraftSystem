package fallcraftsystem.entities;

import fallcraftsystem.entities.enums.*;
import org.bukkit.entity.Player;

public class GamePlayer {
    private SpyStatus spyStatus;
    private Player player;
    private PlayerStatus playerStatus;
    private VanishStatus vanishStatus;
    private FlyStatus flyStatus;
    private PvpStatus pvpStatus;

    public GamePlayer(Player player, PlayerStatus playerStatus, VanishStatus vanishStatus, FlyStatus fLyStatus, PvpStatus pvpStatus, SpyStatus spyStatus) {
        this.player = player;
        this.playerStatus = playerStatus;
        this.vanishStatus = vanishStatus;
        this.flyStatus = fLyStatus;
        this.pvpStatus = pvpStatus;
        this.spyStatus = spyStatus;
    }

    public GamePlayer(Player player) {
        this.player = player;
        this.playerStatus = PlayerStatus.FREE;
        this.vanishStatus = VanishStatus.VISIBLE;
        this.flyStatus = FlyStatus.NOT_FLYING;
        this.pvpStatus = PvpStatus.OFF;
        this.spyStatus = SpyStatus.OFF;
    }


    public SpyStatus getSpyStatus() {
        return spyStatus;
    }

    public void setSpyStatus(SpyStatus spyStatus) {
        this.spyStatus = spyStatus;
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

    public FlyStatus getFlyStatus() {
        return flyStatus;
    }

    public void setFlyStatus(FlyStatus fLyStatus) {
        this.flyStatus = fLyStatus;
    }

    public PvpStatus getPvpStatus() {
        return pvpStatus;
    }

    public void setPvpStatus(PvpStatus pvpStatus) {
        this.pvpStatus = pvpStatus;
    }

    @Override
    public String toString() {
        return "GamePlayer{" +
                "spyStatus=" + spyStatus +
                ", player=" + player +
                ", playerStatus=" + playerStatus +
                ", vanishStatus=" + vanishStatus +
                ", flyStatus=" + flyStatus +
                ", pvpStatus=" + pvpStatus +
                '}';
    }
}
