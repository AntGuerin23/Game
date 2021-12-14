package cegepst.mainGame.entities.items.coin;

import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public class CoinRespawner {

    private static final int RESPAWN_DELAY = 20;
    private static final int LOCATION_DELAY = 60;
    private int nbOfLostCoins;
    private int respawnDelay;
    private int locationDelay;
    private static CoinRespawner instance;
    private Point lastPlayerLocation;
    private boolean timerHasStarted;
    private Player player;

    public static CoinRespawner getInstance() {
        if (instance == null) {
            instance = new CoinRespawner();
        }
        return instance;
    }

    public void update() {
        if (nbOfLostCoins > 0) {
            timerHasStarted = true;
            createCoinBagIfTimerDone();
            decrementTimer();
        }
    }

    public void updatePlayerLocation(Point lastPlayerLocation, Player player) {
        if (locationDelay <= 0) {
            this.lastPlayerLocation = lastPlayerLocation;
            this.player = player;
            locationDelay = LOCATION_DELAY;
        }
        locationDelay--;
    }

    public void notifyAboutCoinDeath() {
        nbOfLostCoins++;
    }

    public boolean isRespawnReady() {
        return !timerHasStarted;
    }

    private void createCoinBagIfTimerDone() {
        if (respawnDelay <= 0) {
            new CoinBag(lastPlayerLocation.x, lastPlayerLocation.y, player, nbOfLostCoins);
            timerHasStarted = false;
            respawnDelay = RESPAWN_DELAY;
            nbOfLostCoins = 0;
        }
    }

    private void decrementTimer() {
        if (timerHasStarted) {
            respawnDelay--;
        }
    }

    private CoinRespawner() {
        respawnDelay = RESPAWN_DELAY;
        locationDelay = LOCATION_DELAY;
        timerHasStarted = false;
        lastPlayerLocation = new Point(0,0);
    }
}
