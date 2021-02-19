package com.csl.game;

import com.csl.core.mcts.BaseAction;

/**
 * @author MaoLongLong
 * @date 2021-02-18 21:16
 */
public class TicTacToeMove extends BaseAction {

    private final int x;

    private final int y;

    private final int value;

    public TicTacToeMove(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getValue() {
        return value;
    }
}
