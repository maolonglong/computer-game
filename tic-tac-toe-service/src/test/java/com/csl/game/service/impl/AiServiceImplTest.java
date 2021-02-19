package com.csl.game.service.impl;

import com.csl.game.TicTacToeState;
import com.csl.game.service.AiService;
import org.junit.jupiter.api.Test;

/**
 * @author MaoLongLong
 * @date 2021-02-18 22:50
 */
class AiServiceImplTest {

    @Test
    void getBestMove1() {
        int[][] board = {
                {1, 0, 0},
                {0, -1, 0},
                {0, 1, -1},
        };
        TicTacToeState state = new TicTacToeState(board, -1);
        AiService service = new AiServiceImpl();
        System.out.println(service.getBestAction(state));
    }


    @Test
    void getBestMove2() {
        int[][] board = {
                {0, 0, 0},
                {0, -1, -1},
                {0, 0, 1},
        };
        TicTacToeState state = new TicTacToeState(board, 1);
        AiService service = new AiServiceImpl();
        System.out.println(service.getBestAction(state));
    }
}