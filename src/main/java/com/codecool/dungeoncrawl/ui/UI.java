package com.codecool.dungeoncrawl.ui;

import com.codecool.dungeoncrawl.data.Cell;
import com.codecool.dungeoncrawl.data.Gate;
import com.codecool.dungeoncrawl.data.actors.Player;
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


    public UI(GameLogic logic, Set<KeyHandler> keyHandlers) {
        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);
        this.logic = logic;
        this.logic.setEnemyHandlerUI(this);
        this.context = canvas.getGraphicsContext2D();
        this.mainStage = new MainStage(canvas);
        this.keyHandlers = keyHandlers;
    }

    public void refreshContext(){

        this.canvas = new Canvas(
                logic.getMapWidth() * Tiles.TILE_WIDTH,
                logic.getMapHeight() * Tiles.TILE_WIDTH);

        this.context = canvas.getGraphicsContext2D();

        this.mainStage = new MainStage(canvas);

        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);

        scene.setOnKeyPressed(this::onKeyPressed);
    }

    public void setUpPain(Stage primaryStage) {
        this.primaryStage = primaryStage;

        Scene scene = mainStage.getScene();
        primaryStage.setScene(scene);

        logic.setup();
        //refresh();
        scene.setOnKeyPressed(this::onKeyPressed);
    }

    private void onKeyPressed(KeyEvent keyEvent) {
        for (KeyHandler keyHandler : keyHandlers) {
            keyHandler.perform(keyEvent, logic, this);
        }
        //refresh();
    }

    public void mapChange(Cell cell){

        List <Gate> filteredGates = logic.getMap().getGates().stream()
                .filter(gate -> gate.getGatePosition().equals(cell.getPosition()))
                .collect(Collectors.toList());

        if(filteredGates.size()> 0 ){
            Gate gate = filteredGates.get(0);
            Player player = logic.getMap().getPlayer();
            logic.setMap(logic.getAllMaps().get(gate.getMapNumber()));
            player.setCell(logic.getMap().getCell(gate.getTargetPosition().getX(), gate.getTargetPosition().getY()));
            refreshContext();
            //refresh();
        }
        System.out.println("no such gate found");
    }

    public void refresh() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (int x = 0; x < logic.getMapWidth(); x++) {
            for (int y = 0; y < logic.getMapHeight(); y++) {
                Cell cell = logic.getCell(x, y);
                if (cell.getActor() != null) {
                    Tiles.drawTile(context, cell.getActor(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
        mainStage.setHealthLabelText(logic.getPlayerHealth());
    }
}
