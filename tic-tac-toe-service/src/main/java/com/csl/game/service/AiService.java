package com.csl.game.service;

import com.csl.game.TicTacToeMove;
import com.csl.game.TicTacToeState;

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
    TicTacToeMove getBestAction(TicTacToeState state);

    /**
     * 返回胜率的估值（粗略地把平局也算成赢）
     *
     * @return AI 胜率
     */
    double getWinRate();
}
