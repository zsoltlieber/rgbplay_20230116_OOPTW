package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Enemy extends Actor {
    public Enemy(Cell cell, EnemyType enemyType) {
        super(cell);
        this.name = enemyType.getName();
        this.health = enemyType.getHealth();
        this.attack = enemyType.getAttack();
        this.defense = enemyType.getDefense();
        this.xpValue = enemyType.getXpValue();
    }

    public static EnemyType getEnemyType(char character) {
        for(EnemyType type : EnemyType.values()) {
            if(type.getSymbol() == character) return type;
        }

        return EnemyType.SKELETON;
    }

    @Override
    public String getTileName() {
        return this.name;
    }
}
