package com.csl.game.ui.controller;

import cn.hutool.core.util.StrUtil;
import com.csl.game.TicTacToeGame;
import com.csl.game.TicTacToeMove;
import com.csl.game.model.Consts;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Shape;

/**
 * @author MaoLongLong
 * @date 2021-02-18 20:22
 */
public class IndexController {

    private TicTacToeGame game;

    @FXML
    private GridPane board;

    @FXML
    private JFXTextArea log;

    @FXML
    private JFXToggleButton firstButton;

    @FXML
    private JFXButton startButton;

    @FXML
    private JFXButton endButton;

    @FXML
    private JFXButton okButton;

    @FXML
    private JFXButton cancelButton;

    private boolean flag;
    private boolean[][] vis;

    private Shape tempShape;
    private int tempI;
    private int tempJ;

    public void initialize() {
//        game = new TicTacToeGame(first.isSelected());
    }

    @FXML
    public void handleClick(MouseEvent event) {
        if (game != null && !flag) {
            int row = (int) (event.getY() / 240);
            int col = (int) (event.getX() / 240);
            if (vis[row][col]) {
                return;
            }
            flag = true;
            tempI = row;
            tempJ = col;
            tempShape = firstButton.isSelected() ? generateCircle() : generatePolyline();
            board.add(tempShape, col, row);
            okButton.setDisable(false);
            cancelButton.setDisable(false);
        }
    }

    @FXML
    public void clearLog(MouseEvent event) {
        log.clear();
    }

    @FXML
    public void gameStart(MouseEvent event) {
        board.getChildren().remove(1, board.getChildren().size());
        System.gc();
        game = new TicTacToeGame(firstButton.isSelected());
        vis = new boolean[3][3];
        if (firstButton.isSelected()) {
            TicTacToeMove move = game.get();
            board.add(generatePolyline(), move.getY(), move.getX());
            vis[move.getX()][move.getY()] = true;
            log.appendText(StrUtil.format("当前 AI 胜率: {}\n", game.getWinRate()));
        }
        flag = false;
        firstButton.setDisable(true);
        startButton.setDisable(true);
        endButton.setDisable(false);
    }

    @FXML
    public void endGame(MouseEvent event) {
        game = null;
        firstButton.setDisable(false);
        startButton.setDisable(false);
        endButton.setDisable(true);
        okButton.setDisable(true);
        cancelButton.setDisable(true);
    }

    private Circle generateCircle() {
        Circle circle = new Circle();
        circle.setFill(null);
        circle.setStroke(Color.SKYBLUE);
        circle.setRadius(100);
        circle.setStrokeWidth(6);
        return circle;
    }

    private Polyline generatePolyline() {
        Polyline polyline = new Polyline();
        polyline.setStrokeWidth(6);
        polyline.setStroke(Color.PINK);
        polyline.getPoints().addAll(0.0, 0.0, 200.0, 200.0,
                100.0, 100.0, 0.0, 200.0, 200.0, 0.0);
        return polyline;
    }

    @FXML
    public void handleOk(MouseEvent event) {
        game.set(tempI, tempJ);
        vis[tempI][tempJ] = true;
        if (checkGameResult()) {
            return;
        }
        TicTacToeMove move = game.get();
        board.add(firstButton.isSelected() ? generatePolyline() : generateCircle(),
                move.getY(), move.getX());
        log.appendText(StrUtil.format("当前 AI 胜率: {}\n", game.getWinRate()));
        vis[move.getX()][move.getY()] = true;
        if (checkGameResult()) {
            return;
        }
        okButton.setDisable(true);
        cancelButton.setDisable(true);
        flag = false;
    }

    @FXML
    public void handleCancel(MouseEvent event) {
        board.getChildren().remove(tempShape);
        okButton.setDisable(true);
        cancelButton.setDisable(true);
        flag = false;
    }

    private boolean checkGameResult() {
        if (game.getGameResult() != Consts.NONE) {
            if (game.getGameResult() == Consts.X) {
                log.appendText("X 赢了\n");
            } else if (game.getGameResult() == Consts.O) {
                log.appendText("O 赢了\n");
            } else {
                log.appendText("平局\n");
            }
            endGame(null);
            return true;
        }
        return false;
    }
}
