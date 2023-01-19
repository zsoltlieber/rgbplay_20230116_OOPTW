package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

import java.util.ArrayList;
import java.util.List;

public class Player extends Actor {

    public Player(Cell cell) {
        super(cell);
    }
    public String getTileName() {
        return "player";
    }

}
