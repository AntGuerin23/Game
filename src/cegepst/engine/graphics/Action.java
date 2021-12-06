package cegepst.engine.graphics;

public enum Action {
    RUN(0, 9),
    PUSH(1, 6),
    JUMP(2, 12),
    STUNNED(3, 12);

    private final int id;
    private final int nbOfFrames;

    Action(int id, int nbOfFrames) {
        this.id = id;
        this.nbOfFrames = nbOfFrames;
    }

    public int getId() {
        return id;
    }

    public int getNbOfFrames() {
        return nbOfFrames;
    }
}
