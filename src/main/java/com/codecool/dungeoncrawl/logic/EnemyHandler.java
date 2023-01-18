package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.EnemyType;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnemyHandler {
    private GameMap map;
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
                    moveEnemies(map);
            }, 1000, enemyType.getFrequency(), TimeUnit.MILLISECONDS);
            i++;
        }
    }

    private void moveEnemies(GameMap map) {
        int width = map.getWidth();
        int height = map.getHeight();
        System.out.println("TOTAL: " + width + "         " + height);
        for(int i = 0; i < height; i++) {
            for(int j = 0; i < width; j++ ) {
                //System.out.println("CURRENT: " + i + "         " + j);
                try {
                    Cell currentCell = map.getCell(i, j);
                    Actor currentCellActor;
                    if((currentCellActor = currentCell.getActor()) != null) {
                        if (currentCell.getType() == CellType.ENEMY) {
                            currentCellActor.move(0, 1);
                        }
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    //e.printStackTrace();
                }
            }break;
        }
    }
    public void test() {
        var wrapper = new Object(){ int num = 0; };
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();

        Runnable task = () -> {
            System.out.println(wrapper.num);
            wrapper.num++;
            if(wrapper.num > 20) executor.shutdownNow();
        };

        executor.scheduleAtFixedRate(task, 0, 500, TimeUnit.MILLISECONDS);
        //executor.shutdownNow();
        //executor.scheduleAtFixedRate(task, 0, 300, TimeUnit.MILLISECONDS);
    }
    public void setMap(GameMap map) {this.map = map;}
    public GameMap getMap() {return this.map;}
}
