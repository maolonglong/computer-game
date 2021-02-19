package com.csl.game.ui.controller;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

/**
 * @author MaoLongLong
 * @date 2021-02-18 20:22
 */
public class IndexController {

    @FXML
    public void handleClick(MouseEvent event) {
        double x = event.getX();
        double y = event.getY();
        System.out.println("click: " + x + ", " + y);
    }
}
