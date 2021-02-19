package com.csl.game.service.impl;

import com.csl.game.service.AiService;
import org.junit.jupiter.api.Test;

/**
 * @author MaoLongLong
 * @date 2021-02-18 22:50
 */
class AiServiceImplTest {

    @Test
    void getBestMove() {
        int[][] board = {
                {1, 0, 0},
                {0, -1, 0},
                {0, 1, -1},
        };
        AiService service = new AiServiceImpl();
        System.out.println(service.getBestMove(board, -1));
    }
}