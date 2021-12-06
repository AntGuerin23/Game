package cegepst.mainGame.miscellaneous.other;

public enum Resource {

    TEST_WORLD_IMG_PATH("images/testMap48.png"),
    TEST_WORLD_JSON_PATH("json/testMap48.json"),
    PLAYER_SPRITE_SHEET("images/classic_guy-sheet42_48.png");

    private final String path;

    Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
