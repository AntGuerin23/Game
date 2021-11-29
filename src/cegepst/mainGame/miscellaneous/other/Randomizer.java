package cegepst.mainGame.miscellaneous.other;

import java.util.Random;

public class Randomizer {

    public static int randomInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max + 1 - min) + min;
    }
}
