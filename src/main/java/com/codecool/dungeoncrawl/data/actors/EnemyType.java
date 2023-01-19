package com.codecool.dungeoncrawl.data.actors;

public enum EnemyType {
    SKELETON("skeleton", 10, 2, 3, 20, 's', 1),
    ZOMBIE("zombie", 10, 2, 3, 30, 'z', 1),
    WOLF("wolf", 10, 2, 3, 5, 'w', 1);

    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int xpValue;
    private final char symbol;
    private final int frequency;

    EnemyType(String name, int health, int attack, int defense, int xpValue, char symbol, int frequency) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.xpValue = xpValue;
        this.symbol = symbol;
        this.frequency = frequency;
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
    public int getFrequency() {
        return frequency;
    }
}
