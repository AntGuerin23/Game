package cegepst.mainGame.entities.items.coin;

import cegepst.mainGame.entities.player.Player;

import java.awt.*;

public class CoinRespawner {

    private static final int DELAY_TIME = 20;
    private int nbOfLostCoins;
    private int delay;
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
        this.lastPlayerLocation = lastPlayerLocation;
        this.player = player;
    }

    public void notifyAboutCoinDeath() {
        nbOfLostCoins++;
    }

    public boolean isRespawnReady() {
        return !timerHasStarted;
    }

    private void createCoinBagIfTimerDone() {
        if (delay <= 0) {
            new CoinBag(lastPlayerLocation.x, lastPlayerLocation.y, player, nbOfLostCoins);
            timerHasStarted = false;
            delay = DELAY_TIME;
            nbOfLostCoins = 0;
        }
    }

    private void decrementTimer() {
        if (timerHasStarted) {
            delay--;
        }
    }

    private CoinRespawner() {
        delay = DELAY_TIME;
        timerHasStarted = false;
    }
}
