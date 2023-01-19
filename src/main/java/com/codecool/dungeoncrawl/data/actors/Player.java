package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    private List<Item> inventory = new ArrayList<>();
    public Player(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "player";
    }

    public void addToInventory(Item item){
        inventory.add(item);
    }

    public void removeFromInventory(Item item){
        inventory.remove(item);
    }
}
