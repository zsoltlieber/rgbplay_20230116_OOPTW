package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;

public class Player extends Actor {
    private int currentXP = 0;
    private int currentLevel = 1;
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
    public void gainXP(int xp) {
        int maxLevel = 15;
        if (currentLevel >= maxLevel) return;
        int xpToNextLevel = ((currentLevel + 1) * 10) / 2;
        while(xp > 0) {
            if(xpToNextLevel - currentXP > xp) {
                currentXP += xp;
                xp = 0;
            } else if(xpToNextLevel - currentXP == xp) {
                currentXP = 0;
                currentLevel++;
                xp = 0;
            } else if(xpToNextLevel - currentXP < xp) {
                xp -= xpToNextLevel - currentXP;
                if(currentLevel < maxLevel) currentLevel++;
            }
        }
    }
}
