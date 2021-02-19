package com.csl.game.ui.controller;

import cn.hutool.core.util.StrUtil;
import com.csl.game.TicTacToeGame;
import com.csl.game.TicTacToeMove;
import com.csl.game.model.Consts;
import com.csl.game.ui.util.ShapeUtil;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
            drawHumanChess(row, col);
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
            doSearch();
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

    @FXML
    public void handleOk(MouseEvent event) {
        game.set(tempI, tempJ);
        vis[tempI][tempJ] = true;
        if (checkGameResult()) {
            return;
        }
        doSearch();
        if (checkGameResult()) {
            return;
        }
        okButton.setDisable(true);
        cancelButton.setDisable(true);
        flag = false;
    }

    private void doSearch() {
        TicTacToeMove move = game.get();
        drawComputerChess(move);
        log.appendText(StrUtil.format("当前 AI 胜率: {}\n", game.getWinRate()));
        vis[move.getX()][move.getY()] = true;
    }

    @FXML
    public void handleCancel(MouseEvent event) {
        board.getChildren().remove(tempShape);
        okButton.setDisable(true);
        cancelButton.setDisable(true);
        flag = false;
    }

    private void drawComputerChess(TicTacToeMove move) {
        board.add(firstButton.isSelected() ?
                        ShapeUtil.generatePolyline() : ShapeUtil.generateCircle(),
                move.getY(), move.getX());
    }

    private void drawHumanChess(int row, int col) {
        tempShape = firstButton.isSelected() ?
                ShapeUtil.generateCircle() : ShapeUtil.generatePolyline();
        board.add(tempShape, col, row);
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
