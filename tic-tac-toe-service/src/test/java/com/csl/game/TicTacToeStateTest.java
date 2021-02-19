package com.csl.game;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author MaoLongLong
 * @date 2021-02-18 21:54
 */
class TicTacToeStateTest {

    @Test
    void getGameResult() {
        TicTacToeState state = new TicTacToeState(-1);
        Assertions.assertEquals(-2, state.getGameResult());

        int[][] board = {{1, 0, -1}, {1, -1, -1}, {0, 1, -1}};
        state = new TicTacToeState(board, 1);
        Assertions.assertEquals(-1, state.getGameResult());

        board = new int[][]{
                {-1, 1, -1},
                {-1, -1, 1},
                {1, -1, 1},
        };
        state = new TicTacToeState(board, 1);
        Assertions.assertEquals(0, state.getGameResult());
    }
}