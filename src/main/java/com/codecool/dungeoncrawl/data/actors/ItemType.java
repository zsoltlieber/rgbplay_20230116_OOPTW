package com.codecool.dungeoncrawl.data.actors;

public enum ItemType {
    DEFAULT("default", 0, 0, 0, 0, 'Ó'),
    HEALTH_POTION("health potion", 10, 0, 0, 0, 'H'),
    CLUB("club", 0, 20, 0, 0, 'C'),
    SWORD("sword", 0, 30, 0, 0, 'S'),
    AXE("axe", 0, 40, 0, 0, 'A'),
    SHIELD("shield", 0, 0, 10, 0, 'D'),
    XP_RING("xp ring", 0, 0, 0, 10, 'X');

    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int xpValue;
    private final char symbol;

    ItemType(String name, int health, int attack, int defense, int xpValue, char symbol) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.xpValue = xpValue;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }
    public int getHealth() {
        return health;
    }
    public int getAttack() {
        return attack;
    }
    public int getDefense() {
        return defense;
    }
    public int getXpValue() {
        return xpValue;
    }
    public char getSymbol() {
        return symbol;
    }
}
