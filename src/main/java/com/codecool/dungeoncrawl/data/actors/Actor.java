package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Drawable;

public abstract class Actor implements Drawable {
    protected String name;
    protected int health = 30;
    protected int attack;
    protected int defense;
    protected Cell cell;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if(nextCell.getType() == CellType.FLOOR || nextCell.getType() == CellType.EMPTY) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getType() == CellType.WALL) {
            System.out.println("You hit a wall!");
        } else {
            System.out.println("Not implemented yet!");
        }
    }

    public int getHealth() {
        return health;
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
}
