package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;

import java.util.List;

public class GameLogic {
    private GameMap map;
    private List<GameMap> allMaps;
    EnemyHandler enemyHandler;

    public GameLogic() {
        this.allMaps = MapLoader.loadAllMaps();
        this.map = allMaps.get(0);
        this.enemyHandler = new EnemyHandler(map);
        enemyHandler.start();
    }

    public double getMapWidth() {
        return map.getWidth();
    }

    public double getMapHeight() {
        return map.getHeight();
    }

    public void setup() {
    }

    public Cell getCell(int x, int y) {
        return map.getCell(x, y);
    }

    public String getPlayerHealth() {
        return Integer.toString(map.getPlayer().getHealth());
    }


    public GameMap getMap() {
        return map;
    }

    public void setMap(GameMap map) {
        this.map = map;
        this.enemyHandler.setMap(map);
    }
    public void syncMaps() {
        this.setMap(this.enemyHandler.getMap());
    }

    public List<GameMap> getAllMaps() {
        return allMaps;
    }
}
