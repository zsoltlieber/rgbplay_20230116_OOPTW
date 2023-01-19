package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("wall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("gate", new Tile(3, 3));
        tileMap.put("player", new Tile(27, 0));
        //enemies
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("zombie", new Tile(25, 9));
        tileMap.put("wolf", new Tile(21, 9));
        //items
        tileMap.put("health potion", new Tile(26,22));
        tileMap.put("club", new Tile(2, 24));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("axe", new Tile(10, 30));
        tileMap.put("shield", new Tile(5, 25));
        tileMap.put("xp ring", new Tile(13, 28));
        //decorations
        tileMap.put("pillar",new Tile(3,13));
        tileMap.put("altar0",new Tile(0,16));
        tileMap.put("altar1",new Tile(1,16));
        tileMap.put("altar2",new Tile(2,16));
        tileMap.put("altar3",new Tile(0,17));
        tileMap.put("altar4",new Tile(17,17));
        tileMap.put("altar5",new Tile(2,17));
        tileMap.put("altar6",new Tile(0,18));
        tileMap.put("altar7",new Tile(1,18));
        tileMap.put("altar8",new Tile(2,18));
        tileMap.put("torch", new Tile(4,15));
        tileMap.put("special-skull", new Tile(22,23));
        tileMap.put("fence", new Tile(0,6));
        tileMap.put("water", new Tile(8,5));
        tileMap.put("canal", new Tile(8,10));
        tileMap.put("skull", new Tile(18,24));
    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());

        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
