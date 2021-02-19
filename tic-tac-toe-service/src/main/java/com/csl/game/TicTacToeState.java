package com.csl.game;

import cn.hutool.core.util.ObjectUtil;
import com.csl.core.mcts.BaseState;
import com.csl.core.util.IntUtil;
import com.csl.game.model.Consts;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author MaoLongLong
 * @date 2021-02-18 21:17
 */
@SuppressWarnings("AlibabaUndefineMagicConstant")
public class TicTacToeState extends BaseState {

    private final int[][] board;

    public TicTacToeState(int[][] board, int nextMove) {
        super(nextMove);
        this.board = ObjectUtil.clone(board);
    }

    public TicTacToeState(int nextMove) {
        super(nextMove);
        this.board = new int[3][3];
    }

    @Override
    public TicTacToeState move(Object action) {
        Preconditions.checkArgument(action instanceof TicTacToeMove);
        TicTacToeMove move = (TicTacToeMove) action;
        int[][] tempBoard = getBoard();
        tempBoard[move.getX()][move.getY()] = move.getValue();
        return new TicTacToeState(tempBoard, -getNextMove());
    }

    @Override
    public boolean isGameOver() {
        return getGameResult() != Consts.NONE;
    }

    @Override
    public List<TicTacToeMove> getLegalActions() {
        List<TicTacToeMove> legalActions = Lists.newArrayList();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    legalActions.add(new TicTacToeMove(i, j, getNextMove()));
                }
            }
        }
        return legalActions;
    }

    public int[][] getBoard() {
        return ObjectUtil.cloneByStream(board);
    }

    @Override
    public int getGameResult() {
        boolean flag = false;
        for (int i = 0; i < 3; i++) {
            int row = 0;
            int col = 0;
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    flag = true;
                }
                row += board[i][j];
                col += board[j][i];
            }
            if (IntUtil.anyEquals(3 * Consts.X, row, col)) {
                return Consts.X;
            }
            if (IntUtil.anyEquals(3 * Consts.O, row, col)) {
                return Consts.O;
            }
        }
        int tr = board[0][0] + board[1][1] + board[2][2];
        int tl = board[0][2] + board[1][1] + board[2][0];
        if (IntUtil.anyEquals(3 * Consts.X, tr, tl)) {
            return Consts.X;
        }
        if (IntUtil.anyEquals(3 * Consts.O, tr, tl)) {
            return Consts.O;
        }
        return flag ? Consts.NONE : Consts.D;
    }
}
