package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.ui.UI;

import java.util.LinkedList;
import java.util.List;

public abstract class Actor implements Drawable {
    List<String> inventory = new LinkedList<>();
    protected int currentXP = 0;
    protected int currentLevel = 1;
    protected int xpValue;
    protected String name;
    protected int health = 30;
    protected int attack = 10;
    protected int defense =0;
    protected Cell cell;
    protected boolean didntMoveThisRound = false;
    protected boolean movedOnX = false;
    protected boolean noticedPlayer = false;

    protected CellType previousStepType = CellType.ALTAR4;

    List<CellType> walkable =List.of(
            CellType.FLOOR,
            CellType.EMPTY,
            CellType.ALTAR0,
            CellType.ALTAR1,
            CellType.ALTAR2,
            CellType.ALTAR3,
            CellType.ALTAR4,
            CellType.ALTAR5,
            CellType.ALTAR6,
            CellType.ALTAR7,
            CellType.ALTAR8
        );

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

        if(walkable.contains(nextCell.getType())) {
            cell.setActor(null);
            cell.setType(previousStepType);
            this.previousStepType = nextCell.getType();
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.WALL) {
            System.out.println("You hit a wall!");
        } else if (nextCell.getType() == CellType.ENEMY) {
            Actor nextCellActor = nextCell.getActor();
            nextCellActor.damageActor(this.attack);

            if(nextCellActor.isDead()) {
                this.gainXP(nextCellActor.getXpValue()); // give xp to player if they kill a monster
                System.out.println("XP gained: " + nextCellActor.getXpValue());

                nextCell.setActor(null);
                nextCell.setType(CellType.FLOOR);
            }
            //set the HUD after it is decided if the enemy is dead
            ui.setPlayerParameters(this.health, this.inventory, this.xpValue, this.attack, this.defense);
            ui.setEnemyParameters(nextCellActor.health, nextCellActor.xpValue, nextCellActor.attack, nextCellActor.defense);
            nextCell.setActor(nextCellActor);
        } else if (nextCell.getType() == CellType.GATE) {
            ui.mapChange(nextCell);
        } else if (nextCell.getType() == CellType.LOCKED_DOOR && inventory.contains("key")) {
            inventory.remove("key");
            cell.setActor(null);
            cell.setType(previousStepType);
            this.previousStepType = CellType.OPEN_DOOR;
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.SPECIAL_SKULL){
            spawnEnemiesForSkull(nextCell);
        } else if (nextCell.getType() == CellType.ITEM) {
            Actor nextCellActor = nextCell.getActor();
            if(nextCellActor.getName().equals("key")) {
                inventory.add(nextCellActor.getName());
            }
            this.healActor(nextCellActor.getHealth());
            this.increaseDefense(nextCellActor.getDefense());
            this.gainXP(nextCellActor.getXpValue());
            this.increaseAttack(nextCellActor.getAttack());
            ui.setPlayerParameters(this.health, this.inventory, this.xpValue, this.attack, this.defense);

            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(CellType.PLAYER);
            cell = nextCell;
        } else {
            System.out.println("Not implemented yet!");
        }
    }

    public void spawnEnemy(Cell cell, Enemy enemy){
        cell.setActor(enemy);
        cell.setType(CellType.ENEMY);
        cell.getGameMap().addToEnemies(enemy);
    }

    public void spawnEnemiesForSkull(Cell skullCell){
        GameMap map = skullCell.getGameMap();
        List<Cell> targetCells = List.of(
                map.getCell(5,9),
                map.getCell(5,10),
                map.getCell(19,9),
                map.getCell(19,10)
        );

        for(Cell cell : targetCells){
           spawnEnemy(cell,new Enemy(cell,EnemyType.ZOMBIE));
        }

    }

    public void moveEnemy(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        CellType currentCellType = cell.getType();
        if(nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.EMPTY) {
            cell.setActor(null);
            cell.setType(CellType.FLOOR);
            nextCell.setActor(this);
            nextCell.setType(currentCellType == CellType.ENEMY ? CellType.ENEMY : CellType.PLAYER);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.PLAYER) {
            Actor nextCellActor = nextCell.getActor();
            nextCellActor.damageActor(this.attack);
                if(nextCellActor.isDead()) {
                    System.out.println("IMPLEMENT GAME OVER");
                }
            nextCell.setActor(nextCellActor);
        }
    }

    public int getHealth() {
        return health;
    }
    public void healActor(int heal) {this.health += heal;}
    public boolean isDead() {
        return health <= 0;
    }
    public int getAttack() {return attack;}
    public void increaseAttack(int attack) {this.attack += attack;}
    public int getDefense() {return defense;}
    public void increaseDefense(int defense) {this.defense += defense;}
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
    public String getName() {return this.name;}
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
    public boolean didntMoveThisRound() {
        return didntMoveThisRound;
    }
    public void setDidntMoveThisRound(boolean didntMoveThisRound) {
        this.didntMoveThisRound = didntMoveThisRound;
    }
    public boolean movedOnX() {
        return movedOnX;
    }
    public void setMovedOnX(boolean movedOnX) {
        this.movedOnX = movedOnX;
    }
    public boolean noticedPlayer() {
        return noticedPlayer;
    }
    public void setNoticedPlayer(boolean noticedPlayer) {
        this.noticedPlayer = noticedPlayer;
    }
}
