package com.codecool.dungeoncrawl.ui.keyeventhandler;

import com.codecool.dungeoncrawl.data.GameMap;
import com.codecool.dungeoncrawl.logic.GameLogic;
import com.codecool.dungeoncrawl.ui.UI;
import javafx.scene.input.KeyEvent;

public interface KeyHandler {
    void perform(KeyEvent event, GameLogic logic, UI ui);
}
