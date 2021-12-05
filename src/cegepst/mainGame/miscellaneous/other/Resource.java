package cegepst.mainGame.miscellaneous.other;

public enum Resource {

    TEST_WORLD_IMG_PATH("images/testMap48.png"),
    TEST_WORLD_JSON_PATH("json/testMap48.json");

    private final String path;

    Resource(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
