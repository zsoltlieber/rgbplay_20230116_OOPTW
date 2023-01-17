package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.Gate;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Skeleton;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MapLoader {

    static String[] mapFiles = new String[]{"/map0.txt","/map1.txt"};

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
                    switch (line.charAt(x)) {
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
                            cell.setType(CellType.FLOOR);
                            new Skeleton(cell);
                            break;
                        case '@':
                            cell.setType(CellType.FLOOR);
                            map.setPlayer(new Player(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
    }

    private static GameMap loadMap(String fileName) {
        InputStream is = MapLoader.class.getResourceAsStream(fileName);
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
