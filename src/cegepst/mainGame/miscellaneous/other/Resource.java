package cegepst.mainGame.miscellaneous.other;

public enum Resource {

    TEST_WORLD_JSON_PATH("json/testMap48.json"),
    SHOP_JSON("json/shop.json"),
    MAIN_WORLD_JSON("json/mainWorld.json"),

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
    CRACKED_BLOCK("images/cracked-block.png"),
    TEST_WORLD_100("images/48100.png"),
    BLOCK_FRAGMENT("images/block-fragment.png"),
    SHOP("images/shop.png"),
    RESPAWN_TEXT("images/respawn-tutorial.png"),
    SHOOT_TEXT("images/shoot-tutorial.png"),
    FLY_TEXT("images/fly-tutorial.png"),
    CLIMB_TEXT("images/climb-tutorial.png"),
    GLOVE_SPRITE("images/gloves.png"),
    FUEL_SPRITE("images/fuel.png"),
    MAIN_WORLD("images/mainWorld.png"),
    OLD_MAN("images/old-man.png"),

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
