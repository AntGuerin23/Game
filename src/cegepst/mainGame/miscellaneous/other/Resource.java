package cegepst.mainGame.miscellaneous.other;

public enum Resource {

    TEST_WORLD_JSON_PATH("json/testMap48.json"),
    TEST_COIN_WORLD_JSON_PATH("json/48100.json"),

    TEST_WORLD_IMG_PATH("images/testMap48.png"),
    PLAYER_SPRITE_SHEET("images/classic_guy-sheet42_48.png"),
    COIN_SPRITE_SHEET("images/coin.png"),
    SHOTGUN_SPRITE("images/shotgun.png"),
    JETPACK_SPRITE("images/jetpack.png"),
    FLAME_SPRITE_SHEET("images/flames-sheet.png"),
    COIN_EFFECT_SPRITE_SHEET("images/coin_effect-sheet.png"),
    COIN_BAG_SPRITE("images/coin-bag.png"),
    RAT_SPRITE_SHEET("images/rat-sheet.png"),
    SPIKE_UP("images/spike-up.png"),
    SPIKE_RIGHT("images/spike-right.png"),
    SPIKE_DOWN("images/spike-down.png"),
    SPIKE_LEFT("images/spike-left.png"),
    DOOR("images/door.png"),
    CRATE("images/crate.png"),
    TEST_WORLD_100("images/48100.png"),

    COIN_SOUND_EFFECT("sounds/coin.wav"),
    JETPACK_SOUND_EFFECT("sounds/jetpack.wav"),
    GUN_SOUND_EFFECT("sounds/shoot.wav"),
    JUMP_SOUND_EFFECT("sounds/jump.wav"),
    LAND_SOUND_EFFECT("sounds/land.wav"),
    MAIN_MUSIC("sounds/main-world-music.wav"),
    SHOP_MUSIC("sounds/shop-music.wav"),
    DAMAGE_SOUND_EFFECT("sounds/damage.wav");




    private final String path;

    Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
