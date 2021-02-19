package com.csl.game.service.impl;

import com.csl.core.mcts.MonteCarloTreeNode;
import com.csl.core.mcts.MonteCarloTreeSearch;
import com.csl.game.TicTacToeMove;
import com.csl.game.TicTacToeState;
import com.csl.game.service.AiService;

/**
 * @author MaoLongLong
 * @date 2021-02-18 22:41
 */
public class AiServiceImpl implements AiService {

    private double winRate;

    @Override
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public TicTacToeMove getBestAction(TicTacToeState state) {
        MonteCarloTreeSearch mcts = new MonteCarloTreeSearch(state);

        MonteCarloTreeNode node = mcts.bestAction(2000);
        winRate = node.winRate();
        int[][] board = state.getBoard();
        int[][] newBoard = ((TicTacToeState) node.getState()).getBoard();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (newBoard[i][j] != board[i][j]) {
                    return new TicTacToeMove(i, j, state.getNextMove());
                }
            }
        }
        return null;
    }

    @Override
    public double getWinRate() {
        return winRate;
    }
}
