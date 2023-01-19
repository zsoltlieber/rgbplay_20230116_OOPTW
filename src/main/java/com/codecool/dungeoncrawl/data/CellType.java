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
    SPECIAL_SKULL("special-skull"),
    FIRE("fire"),
    LOCKED_DOOR("locked-door"),
    OPEN_DOOR("open-door"),
    //decoration
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
    FENCE("fence"),
    WATER("water"),
    CANAL("canal"),
    SKULL("skull"),
    TORCH("torch");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
