package com.codecool.dungeoncrawl.data.actors;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Enemy extends Actor {
    private EnemyType enemyType;
    public Enemy(Cell cell, EnemyType enemyType) {
        super(cell);
        this.name = enemyType.getName();
        this.health = enemyType.getHealth();
        this.attack = enemyType.getAttack();
        this.defense = enemyType.getDefense();
        this.xpValue = enemyType.getXpValue();
        this.enemyType = enemyType;
    }

    public static EnemyType getEnemyType(char character) {
        for(EnemyType type : EnemyType.values()) {
            if(type.getSymbol() == character) return type;
        }

        return EnemyType.SKELETON;
    }

    public static int[] getEnemyMove(int playerX, int playerY, Actor enemy, EnemyType enemyType) {
        int detectionDistance = enemyType.getDetectionDistance();
        int enemyX = enemy.getX();
        int enemyY = enemy.getY();
        int[] move = {0, 0};

        if(detectionDistance < 1) return getMoveTowardsPlayer(playerX, playerY, enemy);

        if(!enemy.noticedPlayer()) {
            boolean playerIsAround = isPlayerAround(playerX, playerY, enemyX, enemyY, detectionDistance);
            enemy.setNoticedPlayer(playerIsAround);
        }

        if (enemy.noticedPlayer()) {
            move = getMoveTowardsPlayer(playerX, playerY, enemy);
        } else {
            move = getRandomMove(enemy.getCell());
        }
        return move;
    }

    private static boolean isPlayerAround(int playerX, int playerY, int enemyX, int enemyY, int detectionDistance) {
        return (
                distanceBetween(playerX, enemyX) <= detectionDistance &&
                distanceBetween(playerY, enemyY) <= detectionDistance
        );
    }
    private static int distanceBetween(int playerCoord, int enemyCoord) {
        playerCoord = Math.abs(playerCoord);
        enemyCoord = Math.abs(enemyCoord);
        return (playerCoord > enemyCoord) ? (playerCoord - enemyCoord) : (enemyCoord - playerCoord);
    }
    private static int[] getMoveTowardsPlayer(int playerX, int playerY, Actor enemy) {
        boolean playerXisNotEqual = playerX > enemy.getX() || playerX < enemy.getX();
        int[] move = {0, 0};
        Cell enemyCell = enemy.getCell();

        if(enemy.getY() == playerY) {
            if( canMoveToCell(enemyCell.getNeighbor(-1, 0)) && playerX < enemy.getX() ) {
                move = new int[]{-1, 0};
            } else if( canMoveToCell(enemyCell.getNeighbor(1, 0)) ) {
                move = new int[]{1, 0};
            }
        } else if(enemy.getX() == playerX) {
            if( canMoveToCell(enemyCell.getNeighbor(0, -1)) && playerY < enemy.getY() ) {
                move = new int[]{0, -1};
            } else if ( canMoveToCell(enemyCell.getNeighbor(0, 1)) ) {
                move = new int[]{0, 1};
            }
        } else if(!enemy.movedOnX()) {
            if(playerXisNotEqual) {
                if( canMoveToCell(enemyCell.getNeighbor(-1, 0)) && playerX < enemy.getX() ) {
                    move = new int[]{-1, 0};
                } else if( canMoveToCell(enemyCell.getNeighbor(1, 0)) ) {
                    move = new int[]{1, 0};
                }
            }
            enemy.setMovedOnX(true);
        } else {
            if( canMoveToCell(enemyCell.getNeighbor(0, -1)) && playerY < enemy.getY() ) {
                move = new int[]{0, -1};
            } else if ( canMoveToCell(enemyCell.getNeighbor(0, 1)) ) {
                move = new int[]{0, 1};
            }
            enemy.setMovedOnX(false);
        }

        return move;
    }
    private static boolean canMoveToCell(Cell cell) {
        return (
                cell.getType() == CellType.PLAYER ||
                        cell.getType() == CellType.FLOOR ||
                        cell.getType() == CellType.EMPTY
        );
    }
    private static int[] getRandomMove(Cell enemyCell) {
        Random random = new Random();
        HashMap<String, Cell> surroundingCells = enemyCell.getSurroundingCells();
        List<int[]> moves = new LinkedList<>();
        int[] defaultMove = {0, 0};
        if(surroundingCells.get("up").getType() == CellType.FLOOR || surroundingCells.get("up").getType() == CellType.EMPTY)
            moves.add(new int[]{0, -1});
        if(surroundingCells.get("down").getType() == CellType.FLOOR || surroundingCells.get("up").getType() == CellType.EMPTY)
            moves.add(new int[]{0, 1});
        if(surroundingCells.get("left").getType() == CellType.FLOOR || surroundingCells.get("up").getType() == CellType.EMPTY)
            moves.add(new int[]{-1, 0});
        if(surroundingCells.get("right").getType() == CellType.FLOOR || surroundingCells.get("up").getType() == CellType.EMPTY)
            moves.add(new int[]{1, 0});

        if(moves.size() < 1) return defaultMove;
        return moves.get(random.nextInt(moves.size()));
    }

    @Override
    public String getTileName() {
        return this.name;
    }

    public EnemyType getEnemyType() {return this.enemyType;}
}
