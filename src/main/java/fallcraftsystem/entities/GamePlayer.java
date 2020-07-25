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
    private InvStatus invStatus;
    private LuzStatus luzStatus;
    private FCScoreboardStatus fcScoreboardStatus;
    private TpaStatus tpaStatus;

    public GamePlayer(SpyStatus spyStatus, Player player, PlayerStatus playerStatus, VanishStatus vanishStatus, FlyStatus flyStatus, PvpStatus pvpStatus, InvStatus invStatus, LuzStatus luzStatus, FCScoreboardStatus fcScoreboardStatus, TpaStatus tpaStatus) {
        this.spyStatus = spyStatus;
        this.player = player;
        this.playerStatus = playerStatus;
        this.vanishStatus = vanishStatus;
        this.flyStatus = flyStatus;
        this.pvpStatus = pvpStatus;
        this.invStatus = invStatus;
        this.luzStatus = luzStatus;
        this.fcScoreboardStatus = fcScoreboardStatus;
        this.tpaStatus = tpaStatus;
    }

    public GamePlayer(Player player) {
        this.player = player;
        this.playerStatus = PlayerStatus.FREE;
        this.vanishStatus = VanishStatus.VISIBLE;
        this.flyStatus = FlyStatus.NOT_FLYING;
        this.pvpStatus = PvpStatus.OFF;
        this.spyStatus = SpyStatus.OFF;
        this.invStatus = InvStatus.VISIBLE;
        this.luzStatus = LuzStatus.OFF;
        this.fcScoreboardStatus = FCScoreboardStatus.ON;
        this.tpaStatus = TpaStatus.ON;
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

    public InvStatus getInvStatus() {
        return invStatus;
    }

    public void setInvStatus(InvStatus invStatus) {
        this.invStatus = invStatus;
    }

    public LuzStatus getLuzStatus() {
        return luzStatus;
    }

    public void setLuzStatus(LuzStatus luzStatus) {
        this.luzStatus = luzStatus;
    }

    public FCScoreboardStatus getFCScoreboardStatus() {
        return fcScoreboardStatus;
    }

    public void setFcScoreboardStatus(FCScoreboardStatus fcScoreboardStatus) {
        this.fcScoreboardStatus = fcScoreboardStatus;
    }

    public TpaStatus getTpaStatus() {return tpaStatus;}

    public void setTpaStatus(TpaStatus tpaStatus) {
        this.tpaStatus = tpaStatus;
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
                ", invStatus=" + invStatus +
                ", luzStatus=" + luzStatus +
                ", fcScoreboardStatus= " + fcScoreboardStatus +
                ", tpaStatus= " + tpaStatus +
                '}';
    }
}
