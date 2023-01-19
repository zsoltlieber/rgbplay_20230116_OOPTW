package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.CellType;
import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.data.Gate;
import com.codecool.dungeoncrawl.data.actors.Player;
import com.codecool.dungeoncrawl.data.actors.Position;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.elements.MainStage;
import com.codecool.dungeoncrawl.ui.keyeventhandler.KeyHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UI {
    private Canvas canvas;
    private GraphicsContext context;

    private MainStage mainStage;
    private GameLogic logic;
    private Stage primaryStage;
    private Set<KeyHandler> keyHandlers;

    private static int VIEWPORT_HEIGHT = 15;
    private static int VIEWPORT_WIDTH = 15;
    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                VIEWPORT_WIDTH * Tiles.TILE_WIDTH,
                VIEWPORT_HEIGHT * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void setUpPain(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);

        logic.setup();
        refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic, this);
        }
        refresh();
    }

    public void mapChange(Cell cell){

        System.out.println(cell.getX());
        System.out.println(cell.getY());

        List <Gate> filteredGates = logic.getMap().getGates().stream()
                .filter(gate -> gate.getGatePosition().equals(cell.getPosition()))
                .collect(Collectors.toList());

        if(filteredGates.size()> 0 ){
            Gate gate = filteredGates.get(0);
            Player player = logic.getMap().getPlayer();

            logic.setMap(logic.getAllMaps().get(gate.getMapNumber()));
            player.setCell(logic.getMap().getCell(gate.getTargetPosition().getX(), gate.getTargetPosition().getY()));
            player.getCell().setType(CellType.PLAYER);
            logic.getMap().setPlayer(player);
            logic.getMap().getCell(player.getX(), player.getY()).setActor(player);
            refresh();
        }else{
            System.out.println("no such gate found");
        }

    }

    public void refresh() {

        int halfOfTheViewPortWidth = VIEWPORT_WIDTH /2;
        int halfOfTheViewPortHeight = VIEWPORT_HEIGHT /2;
        Position playerPosition = logic.getMap().getPlayer().getCell().getPosition();

        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for(int targetX = 0; targetX < VIEWPORT_WIDTH;targetX++){
            for(int targetY = 0; targetY < VIEWPORT_HEIGHT;targetY++){

                int sourceX = playerPosition.getX()-halfOfTheViewPortWidth + targetX;
                int sourceY = playerPosition.getY()-halfOfTheViewPortHeight  +targetY;

                Cell cell = new Cell(new GameMap(1,1, CellType.EMPTY),0,0, CellType.EMPTY);

                if(!(sourceX <0 || sourceY< 0 || sourceX>= logic.getMapWidth() || sourceY>= logic.getMapHeight())){
                    cell = logic.getCell(sourceX, sourceY);
                }

                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(),targetX, targetY);
                } else {
                    Tiles.drawTile(context, cell, targetX, targetY);
                }
            }
        }

        /*for (int x = playerPosition.getX()-halfOfTheViewPortWidth; x <= playerPosition.getX()+halfOfTheViewPortWidth; x++) {
            for (int y = playerPosition.getY()-halfOfTheViewPortHeight; y <= playerPosition.getY()+halfOfTheViewPortHeight; y++) {

                Cell cell = new Cell(new GameMap(1,1,CellType.EMPTY),0,0, CellType.EMPTY);

                if(!(x <0 || y< 0 || x>= logic.getMapWidth() || y>= logic.getMapHeight())){
                    cell = logic.getCell(x, y);
                }

                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());*/
    }
}
