package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Actor;
import com.codecool.dungeoncrawl.data.actors.Enemy;
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
        for(EnemyType enemyType : EnemyType.values()) {
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis( enemyType.getFrequency() ), ev -> {
                System.out.println("executed " + enemyType.getName() + " movement");
                moveEnemies(map, enemyType);
                ui.refresh();
            }));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        }
    }

        /*for(EnemyType enemyType : EnemyType.values()) {
            System.out.println(enemyType.getName().toUpperCase());
            executor.scheduleAtFixedRate(() -> {
                    moveEnemies(map, enemyType);
            }, 0, enemyType.getFrequency(), TimeUnit.SECONDS);
        }*/

    private void moveEnemies(GameMap map, EnemyType enemyType) {
        int width = map.getWidth();
        int height = map.getHeight();

        for(int i = 0; i < width; i++) {
            for(int j = 0; j < height; j++ ) {
                try {
                    Cell currentCell = map.getCell(i, j);
                    Actor currentCellActor = currentCell.getActor();
                    if(
                            currentCellActor != null &&
                            currentCell.getType() == CellType.ENEMY &&
                            currentCellActor.didntMoveThisRound() &&
                            currentCellActor.getName().equals(enemyType.getName())
                    ) {
                        int playerX = map.getPlayer().getX();
                        int playerY = map.getPlayer().getY();
                        int[] enemyMoveXY = Enemy.getEnemyMove(playerX, playerY, currentCellActor, enemyType);

                        currentCellActor.moveEnemy(enemyMoveXY[0], enemyMoveXY[1]);
                        currentCellActor.setDidntMoveThisRound(false);
                    }
                } catch (ArrayIndexOutOfBoundsException e) {e.printStackTrace();}
            }
        }
        resetEnemiesOfTypeInMap(enemyType);
    }
    private void resetEnemiesOfTypeInMap(EnemyType enemyType) {
        List<Enemy> mapEnemyList = map.getEnemies();
        for(Enemy enemy : mapEnemyList) {
            if(enemy.getName().equals(enemyType.getName()))
                enemy.setDidntMoveThisRound(true);
        }
        map.setEnemies(mapEnemyList);
    }
    public void setMap(GameMap map) {this.map = map;}
    public GameMap getMap() {return this.map;}
    public void setUI(UI ui) {this.ui = ui;}
}
