package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Item extends Actor {
    private final int xpValue;
    public Item(Cell cell, ItemType itemType) {
        super(cell);
        this.name = itemType.getName();
        this.health = itemType.getHealth();
        this.attack = itemType.getAttack();
        this.defense = itemType.getDefense();
        this.xpValue = itemType.getXpValue();
    }

    public static ItemType getItemType(char character) {
        for(ItemType type : ItemType.values()) {
            if(type.getSymbol() == character) return type;
        }

        return ItemType.DEFAULT;
    }

    @Override
    public String getTileName() {
        return this.name;
    }
}
