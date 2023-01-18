package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.EnemyType;
import com.codecool.dungeoncrawl.ui.UI;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnemyHandler {
    private GameMap map;
    private UI ui;
    private List<ScheduledExecutorService> executors = new LinkedList<>();
    public EnemyHandler(GameMap map) {
        this.map = map;
    }

    public void start() {
        int i = 0;
        for(EnemyType enemyType : EnemyType.values()) {
            ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
            executors.add(executor);
            executors.get(i).scheduleAtFixedRate(() -> {
                    moveEnemies(map, enemyType.getName());
                    /*try {
                        ui.refresh();
                    } catch(Exception e) {
                        System.out.println("Error when trying to refresh in enemy handler.");
                    }*/
            }, 0, enemyType.getFrequency(), TimeUnit.MILLISECONDS);
            i++;
        }
    }

    private void moveEnemies(GameMap map, String enemyName) {
        int width = map.getWidth();
        int height = map.getHeight();
        Random random = new Random();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++ ) {
                try {
                    Cell currentCell = map.getCell(i, j);
                    Actor currentCellActor;
                    if((currentCellActor = currentCell.getActor()) != null) {
                        if (currentCell.getType() == CellType.ENEMY && currentCellActor.getName().equals(enemyName)) {
                            System.out.println(currentCellActor.getName());
                            switch(random.nextInt(4)) {
                                case 0:
                                    currentCellActor.move(1, 0);
                                    break;
                                case 1:
                                    currentCellActor.move(-1, 0);
                                    break;
                                case 2:
                                    currentCellActor.move(0, 1);
                                    break;
                                case 3:
                                    currentCellActor.move(0, -1);
                                    break;
                            }
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void setMap(GameMap map) {this.map = map;}
    public GameMap getMap() {return this.map;}
    public void setUI(UI ui) {this.ui = ui;}
}
