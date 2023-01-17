package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Down implements KeyHandler {
    public static final KeyCode code1 = KeyCode.DOWN;
    public static final KeyCode code2 = KeyCode.S;

    @Override
    public void perform(KeyEvent event, GameMap map) {
        if (code1.equals(event.getCode()) || code2.equals(event.getCode()))
            map.getPlayer().move(0, 1);
    }
}
