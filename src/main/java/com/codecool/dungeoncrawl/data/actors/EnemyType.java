package com.codecool.dungeoncrawl.data.actors;

public enum EnemyType {
    SKELETON("skeleton", 10, 2, 3, 20, 's'),
    ZOMBIE("zombie", 10, 2, 3, 30, 'z'),
    WOLF("wolf", 10, 2, 3, 5, 'w');

    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int xpValue;
    private final char symbol;

    EnemyType(String name, int health, int attack, int defense, int xpValue, char symbol) {
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
