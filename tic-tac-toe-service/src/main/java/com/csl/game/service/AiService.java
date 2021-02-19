package com.csl.game.service;

import com.csl.game.TicTacToeMove;

/**
 * @author MaoLongLong
 * @date 2021-02-18 22:38
 */
public interface AiService {

    /**
     * 调用蒙特卡洛树搜索
     *
     * @param board    棋盘
     * @param nextMove 下一轮玩家
     * @return 最佳的走法
     */
    TicTacToeMove getBestMove(int[][] board, int nextMove);
}
