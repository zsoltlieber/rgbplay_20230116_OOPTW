package com.codecool.dungeoncrawl.data.actors;

public enum EnemyType {
    SKELETON("skeleton", 300, 1, 3, 20, 's', -1, 1200),
    ZOMBIE("zombie", 100, 5, 3, 30, 'z', 3, 900),
    WOLF("wolf", 50, 3, 3, 20, 'w', 5, 600);

    private final String name;
    private final int health;
    private final int attack;
    private final int defense;
    private final int xpValue;
    private final char symbol;

    private final int detectionDistance;
    private final int frequency;

    EnemyType(String name, int health, int attack, int defense, int xpValue, char symbol, int detectionDistance, int frequency) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.xpValue = xpValue;
        this.symbol = symbol;
        this.detectionDistance = detectionDistance;
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
    public int getDetectionDistance() {
        return detectionDistance;
    }
    public int getFrequency() {
        return frequency;
    }
}
