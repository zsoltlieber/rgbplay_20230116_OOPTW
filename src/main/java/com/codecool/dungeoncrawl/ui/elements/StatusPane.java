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

    private Label xPTextLabel;
    private Label xPValueLabel;
    private Label attackTextLabel;
    private Label attackValueLabel;
    private Label defenceTextLabel;
    private Label defenceValueLabel;
    private Label rowSign;
    private Label enemyHealthTextLabel;
    private Label enemyHealthValueLabel;
    private Label enemyXPTextLabel;
    private Label enemyXPValueLabel;
    private Label enemyAttackTextLabel;
    private Label enemyAttackValueLabel;
    private Label enemyDefenceTextLabel;
    private Label enemyDefenceValueLabel;
    private Label inventoryTextLabel;
    private Label inventoryValueLabel;

    public StatusPane() {
        ui = new GridPane();
        healthTextLabel = new Label("Health: ");
        healthValueLabel = new Label();
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
        enemyXPTextLabel = new Label("Enemy XP");
        enemyXPValueLabel = new Label();
        enemyAttackTextLabel = new Label("Enemy Attack");
        enemyAttackValueLabel = new Label();
        enemyDefenceTextLabel = new Label("Enemy Defence");
        enemyDefenceValueLabel = new Label();

    }

    public BorderPane build() {

        ui.setPrefWidth(RIGHT_PANEL_WIDTH);
        ui.setPadding(new Insets(RIGHT_PANEL_PADDING));

        ui.add(healthTextLabel, 0, 0);
        ui.add(healthValueLabel, 1, 0);
        ui.add(xPTextLabel, 0, 2);
        ui.add(xPValueLabel, 1, 2);
        ui.add(attackTextLabel, 0, 4);
        ui.add(attackValueLabel, 1, 4);
        ui.add(defenceTextLabel, 0, 6);
        ui.add(defenceValueLabel, 1, 6);
        ui.add(inventoryTextLabel, 0, 8);
        ui.add(inventoryValueLabel, 1, 8);
        ui.add(rowSign, 0, 10);
        ui.add(enemyHealthTextLabel, 0, 12);
        ui.add(enemyHealthValueLabel, 1, 12);
        ui.add(enemyXPTextLabel, 0, 14);
        ui.add(enemyXPValueLabel, 1, 14);
        ui.add(enemyAttackTextLabel, 0, 16);
        ui.add(enemyAttackValueLabel, 1, 16);
        ui.add(enemyDefenceTextLabel, 0, 18);
        ui.add(enemyDefenceValueLabel, 1, 18);

        BorderPane borderPane = new BorderPane();
        borderPane.setRight(ui);
        return borderPane;
    }

    public void setHealthValueLabel(String text) {
        healthValueLabel.setText(text);
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
    public void setEnemyHealthValueLabel(String text) {
        enemyHealthValueLabel.setText(text);
    }

    public void setEnemyXPValueLabel(String text) {
        enemyXPValueLabel.setText(text);
    }

    public void setEnemyAttackValueLabel(String text) {
        enemyAttackValueLabel.setText(text);
    }

    public void setEnemyDefenceValueLabel(String text) {
        enemyDefenceValueLabel.setText(text);
    }

    public void setInventoryValueLabel(String text) {inventoryValueLabel.setText(text);}
}
