package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.UI;

public abstract class Actor implements Drawable {
    protected int currentXP = 0;
    protected int currentLevel = 1;
    protected int xpValue;
    protected String name;
    protected int health = 30;
    protected int attack = 10;
    protected int defense =0;
    protected Cell cell;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void move(int dx, int dy, UI ui) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType currentCellType = cell.getType();
        if(nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.EMPTY) {
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(currentCellType == CellType.ENEMY ? CellType.ENEMY : CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.WALL) {
            System.out.println("You hit a wall!");
        } else if (nextCell.getType() == CellType.ENEMY || nextCell.getType() == CellType.PLAYER) {
            Actor nextCellActor = nextCell.getActor();
            nextCellActor.damageActor(this.attack);
            if(currentCellType == CellType.PLAYER) {
                ui.setPlayerParameters(this.health, this.xpValue, this.attack, this.defense);
                ui.setEnemyParameters(nextCellActor.health, nextCellActor.xpValue, nextCellActor.attack, nextCellActor.defense);
                if(nextCellActor.isDead()) {
                    this.gainXP(nextCellActor.getXpValue()); // give xp to player if they kill a monster
                    System.out.println("XP gained: " + nextCellActor.getXpValue());
                    nextCell.setActor(null);
                    nextCell.setType(CellType.FLOOR);
                }
            } else if (currentCellType == CellType.ENEMY) {
            ui.setPlayerParameters(nextCellActor.health, nextCellActor.xpValue, nextCellActor.attack, nextCellActor.defense);
            ui.setEnemyParameters(this.health, this.xpValue, this.attack, this.defense);
                if(nextCellActor.isDead()) {
                    System.out.println("IMPLEMENT GAME OVER");
                }
            }
            nextCell.setActor(nextCellActor);
        } else if (nextCell.getType() == CellType.GATE && currentCellType == CellType.PLAYER) {
            ui.mapChange(nextCell);
        } else {
            System.out.println("Not implemented yet!");
        }
    }

    public int getHealth() {
        return health;
    }
    public boolean isDead() {
        return (health <= 0) ? true : false;
    }
    public int getAttack() {return attack;}
    public int getDefense() {return defense;}
    public Cell getCell() {
        return cell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
    public int getXpValue() {return this.xpValue;}
    public int getCurrentXP() {return this.currentXP;}
    public int getCurrentLevel() {return this.currentLevel;}
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
    public void damageActor(int damageNumber) {
        this.health -= damageNumber - this.defense;
    }
}
