package cegepst.mainGame.miscellaneous.other;

public enum Resource {

    PLAYER_SPRITESHEET("images/player.png"),
    MAIN_BACKGROUND_MUSIC("musics/map1.wav"),
    TREE_SPRITE("images/tree.png"),
    WORLD_SPRITE("images/demo.png");

    private final String path;

    Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
