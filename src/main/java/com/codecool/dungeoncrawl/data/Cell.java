package com.codecool.dungeoncrawl.data;

import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.EnemyType;
import com.codecool.dungeoncrawl.data.actors.Item;
import com.codecool.dungeoncrawl.data.actors.Position;

import java.util.HashMap;

public class Cell implements Drawable {
    private CellType type;
    private Actor actor;
    private GameMap gameMap;
    private Position position;

    public Position getPosition() {
        return position;
    }

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.position = new Position(x,y);
        this.type = type;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public Actor getActor() {
        return actor;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(position.getX() + dx, position.getY() + dy);
    }

    public HashMap<String, Cell> getSurroundingCells() {
        HashMap<String, Cell> surrounding = new HashMap<>();
        surrounding.put("up", gameMap.getCell(position.getX(), position.getY() + -1));
        surrounding.put("down", gameMap.getCell(position.getX(), position.getY() + 1));
        surrounding.put("left", gameMap.getCell(position.getX() + -1, position.getY()));
        surrounding.put("right", gameMap.getCell(position.getX() + 1, position.getY()));

        return surrounding;
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }
}
