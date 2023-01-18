package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    PLAYER("player"),
    ITEM("item"),
    ENEMY("enemy"),
    GATE("gate"),
    FLOOR("floor"),
    WALL("wall");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
