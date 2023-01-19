package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.actors.Item;
import com.codecool.dungeoncrawl.data.Gate;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Enemy;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {

    static String[] mapFiles = new String[]{
            "map0.txt",
            "map1.txt",
            "map2.txt",
            "map3.txt",
            "map4.txt",
            "map5.txt",
            "map6.txt"
            };

    public static List<GameMap> loadAllMaps(){
        List<GameMap> output = new ArrayList<>();

        for(String file : mapFiles){
            output.add(loadMap(file));
        }

        return output;
    }

    private static void loadGates(GameMap map, Scanner scanner, int numberOfGates){
        for(int i =0 ; i< numberOfGates; i++){
            int gateX = scanner.nextInt();
            int gateY = scanner.nextInt();
            int targetX = scanner.nextInt();
            int targetY = scanner.nextInt();
            int mapNumber = scanner.nextInt();
            map.addGateToMap(new Gate(gateX,gateY,targetX,targetY,mapNumber));
            scanner.nextLine(); // empty line
        }
    }

    private static void loadCells(GameMap map,int width,int height, Scanner scanner){
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    char currentChar = line.charAt(x);
                    switch (currentChar) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case '.':
                            cell.setType(CellType.FLOOR);
                            break;
                        case 's':
                        case 'z':
                        case 'w':
                            cell.setType(CellType.ENEMY);
                            map.addToEnemies(new Enemy(cell, Enemy.getEnemyType(currentChar)));
                            break;
                        case 'H':
                        case 'C':
                        case 'S':
                        case 'A':
                        case 'D':
                        case 'X':
                            cell.setType(CellType.ITEM);
                            new Item(cell, Item.getItemType(currentChar));
                            break;
                        case '$':
                            cell.setType(CellType.GATE);
                            break;
                        case '@':
                            cell.setType(CellType.PLAYER);
                            map.setPlayer(new Player(cell));
                            break;
                        case 'p':
                            cell.setType(CellType.PILLAR);
                            break;
                        case '0':
                            cell.setType(CellType.ALTAR0);
                            break;
                        case '1':
                            cell.setType(CellType.ALTAR1);
                            break;
                        case '2':
                            cell.setType(CellType.ALTAR2);
                            break;
                        case '3':
                            cell.setType(CellType.ALTAR3);
                            break;
                        case '4':
                            cell.setType(CellType.ALTAR4);
                            break;
                        case '5':
                            cell.setType(CellType.ALTAR5);
                            break;
                        case '6':
                            cell.setType(CellType.ALTAR6);
                            break;
                        case '7':
                            cell.setType(CellType.ALTAR7);
                            break;
                        case '8':
                            cell.setType(CellType.ALTAR8);
                            break;
                        case 't':
                            cell.setType(CellType.TORCH);
                            break;
                        case 'o':
                            cell.setType(CellType.SPECIAL_SKULL);
                            break;
                        case 'f':
                            cell.setType(CellType.FENCE);
                            break;
                        case 'v':
                            cell.setType(CellType.WATER);
                            break;
                        case 'c':
                            cell.setType(CellType.CANAL);
                            break;
                        case 'h':
                            cell.setType(CellType.SKULL);
                            break;
                        case 'F':
                            cell.setType(CellType.FIRE);
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + currentChar + "'");
                    }
                }
            }
        }
    }

    private static GameMap loadMap(String fileName) {
        InputStream is = MapLoader.class.getResourceAsStream("/maps/" + fileName);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int numberOfGates = scanner.nextInt();

        GameMap map = new GameMap(width, height, CellType.EMPTY);

        scanner.nextLine(); // empty line

        loadGates(map,scanner,numberOfGates);

        loadCells(map,width,height,scanner);

        return map;
    }

}
