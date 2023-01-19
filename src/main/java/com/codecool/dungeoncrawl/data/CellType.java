package com.codecool.dungeoncrawl.data;

public enum CellType {
    EMPTY("empty"),
    PLAYER("player"),
    ITEM("item"),
    ENEMY("enemy"),
    CORPSE("corpse"),
    GATE("gate"),
    FLOOR("floor"),
    WALL("wall"),
    PILLAR("pillar"),
    ALTAR0("altar0"),
    ALTAR1("altar1"),
    ALTAR2("altar2"),
    ALTAR3("altar3"),
    ALTAR4("altar4"),
    ALTAR5("altar5"),
    ALTAR6("altar6"),
    ALTAR7("altar7"),
    ALTAR8("altar8"),
    SPECIAL_SKULL("special-skull"),
    FENCE("fence"),
    WATER("water"),
    CANAL("canal"),
    TORCH("torch");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
