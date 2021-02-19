package com.csl.game;

import com.csl.game.model.Consts;
import com.csl.game.service.AiService;
import com.csl.game.service.impl.AiServiceImpl;
import com.google.common.base.Preconditions;

import java.text.DecimalFormat;

/**
 * @author MaoLongLong
 * @date 2021-02-19 14:26
 */
public class TicTacToeGame {

    private final AiService aiService;

    private TicTacToeState state;

    private boolean first;

    private int nextMove;

    /**
     * @param first AI 是否先手，先手方使用 X，后手方使用 O
     */
    public TicTacToeGame(boolean first) {
        aiService = new AiServiceImpl();
        nextMove = Consts.X;
        this.first = first;
        state = new TicTacToeState(nextMove);
    }

    public void set(int i, int j) {
        Preconditions.checkArgument(first ? nextMove == Consts.O : nextMove == Consts.X);
        state = state.move(new TicTacToeMove(i, j, nextMove));
        nextMove = -nextMove;
    }

    public TicTacToeMove get() {
        Preconditions.checkArgument(first ? nextMove == Consts.X : nextMove == Consts.O);
        TicTacToeMove move = aiService.getBestMove(state.getBoard(), nextMove);
        state = state.move(move);
        nextMove = -nextMove;
        return move;
    }

    public int getGameResult() {
        return state.getGameResult();
    }

    public String getWinRate() {
        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(aiService.getWinRate());
    }
}
