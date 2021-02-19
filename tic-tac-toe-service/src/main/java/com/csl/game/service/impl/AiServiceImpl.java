package com.csl.game.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import com.csl.core.mcts.MonteCarloTreeNode;
import com.csl.core.mcts.MonteCarloTreeSearch;
import com.csl.game.TicTacToeMove;
import com.csl.game.TicTacToeState;
import com.csl.game.service.AiService;
import com.google.common.base.Preconditions;
import lombok.extern.slf4j.Slf4j;

import java.text.DecimalFormat;

/**
 * @author MaoLongLong
 * @date 2021-02-18 22:41
 */
@Slf4j
public class AiServiceImpl implements AiService {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##%");

    @Override
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public TicTacToeMove getBestMove(int[][] board, int nextMove) {
        Preconditions.checkArgument(board.length == 3 && board[0].length == 3);
        TicTacToeState state = new TicTacToeState(board, nextMove);
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(state);

        TimeInterval timer = DateUtil.timer();
        MonteCarloTreeNode newNode = mcts.bestAction(2000);
        log.info("time: {}ms", timer.intervalMs());

        int[][] newBoard = ((TicTacToeState) newNode.getState()).getBoard();

        log.info("win rate: {}", DECIMAL_FORMAT.format(newNode.winRate()));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (newBoard[i][j] != board[i][j]) {
                    TicTacToeMove move = new TicTacToeMove(i, j, nextMove);
                    log.info("return {}", move);
                    return move;
                }
            }
        }
        return null;
    }
}
