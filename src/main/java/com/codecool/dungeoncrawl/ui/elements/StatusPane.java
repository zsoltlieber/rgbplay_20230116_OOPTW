package com.codecool.dungeoncrawl.ui.elements;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class StatusPane {
    public static final int RIGHT_PANEL_WIDTH = 200;
    public static final int RIGHT_PANEL_PADDING = 10;
    private GridPane ui;
    private Label healthTextLabel;
    private Label healthValueLabel;
    private Label levelTextLabel;
    private Label levelValueLabel;
    private Label xPTextLabel;
    private Label xPValueLabel;
    private Label attackTextLabel;
    private Label attackValueLabel;
    private Label defenceTextLabel;
    private Label defenceValueLabel;

    private Label inventoryTextLabel;
    private Label inventoryValueLabel;

    private Label rowSign;
    private Label enemyHealthTextLabel;
    private Label enemyHealthValueLabel;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
        levelTextLabel = new Label("Level: ");
        levelValueLabel = new Label();
        xPTextLabel = new Label("Experience: ");
        xPValueLabel = new Label();
        attackTextLabel = new Label("Attack: ");
        attackValueLabel = new Label();
        defenceTextLabel = new Label("Defence: ");
        defenceValueLabel = new Label();
        inventoryTextLabel = new Label("Inventory: ");
        inventoryValueLabel = new Label();
        rowSign = new Label("=============");
        enemyHealthTextLabel = new Label("Enemy health");
        enemyHealthValueLabel = new Label();
    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(levelTextLabel, 0, 2);
        ui.add(levelValueLabel, 1, 2);
        ui.add(xPTextLabel, 0, 4);
        ui.add(xPValueLabel, 1, 4);
        ui.add(attackTextLabel, 0, 6);
        ui.add(attackValueLabel, 1, 6);
        ui.add(defenceTextLabel, 0, 8);
        ui.add(defenceValueLabel, 1, 8);
        ui.add(inventoryTextLabel, 0, 10);
        ui.add(inventoryValueLabel, 1, 10);
        ui.add(rowSign, 0, 12);
        ui.add(enemyHealthTextLabel, 0, 14);
        ui.add(enemyHealthValueLabel, 1, 14);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValueLabel(String text) {
        healthValueLabel.setText(text);
    }
    public void setLevelValueLabel(String text) {
        levelValueLabel.setText(text);
    }
    public void setXPValueLabel(String text) {
        xPValueLabel.setText(text);
    }

    public void setAttackValueLabel(String text) {
        attackValueLabel.setText(text);
    }

    public void setDefenceValueLabel(String text) {
        defenceValueLabel.setText(text);
    }
    public void setInventoryValueLabel(String text) {
        inventoryValueLabel.setText(text);
    }
    public void setEnemyHealthValueLabel(String text) {
        enemyHealthValueLabel.setText(text);
    }
}
