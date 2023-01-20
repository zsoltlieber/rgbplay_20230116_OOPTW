package com.codecool.dungeoncrawl.ui.elements;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.BorderPane;

public class MainStage {
    private Canvas canvas;
    private Scene scene;
    private StatusPane statusPane;

    public MainStage(Canvas canvas) {
        this.canvas = canvas;
        statusPane = new StatusPane();
        scene = setUpScene();
    }

    private Scene setUpScene() {
        BorderPane borderPane = statusPane.build();
        borderPane.setCenter(canvas);
        Scene scene = new Scene(borderPane);
        return scene;
    }

    public Scene getScene() {
        return scene;
    }
    public void setHealthValueLabel(String text) {
        this.statusPane.setHealthValueLabel(text);
    }
    public void setInventoryValueLabel(String text) {
        this.statusPane.setInventoryValueLabel(text);
    }
    public void setLevelValueLabel(String text) {
        this.statusPane.setLevelValueLabel(text);
    }
    public void setAttackValueLabel(String text) {
        this.statusPane.setAttackValueLabel(text);
    }
    public void setDefenceValueLabel(String text) {
        this.statusPane.setDefenceValueLabel(text);
    }
    public void setEnemyHealthValueLabel(String text) {    this.statusPane.setEnemyHealthValueLabel(text);    }
    public void setEnemyXPValueLabel(String text) {    this.statusPane.setEnemyXPValueLabel(text);    }
    public void setEnemyAttackValueLabel(String text) {    this.statusPane.setEnemyAttackValueLabel(text);    }
    public void setEnemyDefenceValueLabel(String text) {     this.statusPane.setEnemyDefenceValueLabel(text);   }

    public void setHealthLabelText(String playerHealth) {
    }
}