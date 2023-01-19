package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.EnemyType;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import org.w3c.dom.ls.LSOutput;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class EnemyHandler {
    private GameMap map;
    private UI ui;
    private ScheduledExecutorService executor = Executors.newScheduledThreadPool(EnemyType.values().length);
    public EnemyHandler(GameMap map) {
        this.map = map;
    }

    public void start() {
        System.out.println("START ENEMY HANDLER");
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            System.out.println("executed monster movement");
            moveEnemies(map);
            ui.refresh();
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

        /*for(EnemyType enemyType : EnemyType.values()) {
            System.out.println(enemyType.getName().toUpperCase());
            executor.scheduleAtFixedRate(() -> {
                    moveEnemies(map, enemyType);
            }, 0, enemyType.getFrequency(), TimeUnit.SECONDS);
        }*/

    private void moveEnemies(GameMap map) {
        int width = map.getWidth();
        int height = map.getHeight();
        Random random = new Random();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++ ) {
                try {
                    Cell currentCell = map.getCell(i, j);
                    Actor currentCellActor = currentCell.getActor();
                    if(currentCellActor != null && currentCell.getType() == CellType.ENEMY) {
                        //if (currentCell.getType() == CellType.ENEMY && currentCellActor.getName().equals(enemyType.getName())) {

                            if(currentCellActor.getName().equals(EnemyType.WOLF.getName()))
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
                        //}
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
