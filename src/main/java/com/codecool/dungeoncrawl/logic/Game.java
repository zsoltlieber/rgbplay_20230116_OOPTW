package com.codecool.dungeoncrawl.logic;

import com.codecool.dungeoncrawl.ui.UI;
import com.codecool.dungeoncrawl.ui.keyeventhandler.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Game extends Application {
    private UI ui;
    private GameLogic logic;
    private Set<KeyHandler> keyHandlers;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.keyHandlers = Set.of(new Up(), new Down(), new Left(), new Right());
        this.logic = new GameLogic();
        this.ui = new UI(logic, keyHandlers);
        ui.setUpPain(primaryStage);

        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        //executor.scheduleAtFixedRate(() -> {
        //    ui.refresh();
        //},0, 40, TimeUnit.MILLISECONDS);
        // 60 FPS - 16.6 MS

        primaryStage.setTitle("Dungeon Crawl");
        primaryStage.show();
    }
}
