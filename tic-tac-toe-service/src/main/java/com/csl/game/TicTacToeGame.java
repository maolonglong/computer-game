package com.csl.game;

import cn.hutool.core.lang.Assert;
import com.csl.game.model.Consts;
import com.csl.game.service.AiService;
import com.csl.game.service.impl.AiServiceImpl;

import java.text.DecimalFormat;

/**
 * @author MaoLongLong
 * @date 2021-02-19 14:26
 */
public class TicTacToeGame {

    private final AiService aiService;

    private TicTacToeState state;

    private final boolean first;

    /**
     * @param first AI 是否先手，先手方使用 X，后手方使用 O
     */
    public TicTacToeGame(boolean first) {
        aiService = new AiServiceImpl();
        this.first = first;
        state = new TicTacToeState(Consts.X);
    }

    public int nextMove() {
        return state.getNextMove();
    }

    public void set(int i, int j) {
        Assert.isTrue(first ? nextMove() == Consts.O : nextMove() == Consts.X);
        state = state.move(new TicTacToeMove(i, j, nextMove()));
    }

    public TicTacToeMove get() {
        Assert.isTrue(first ? nextMove() == Consts.X : nextMove() == Consts.O);
        TicTacToeMove action = aiService.getBestAction(state);
        state = state.move(action);
        return action;
    }

    public int getGameResult() {
        return state.getGameResult();
    }

    public String getWinRate() {
        DecimalFormat df = new DecimalFormat("#.##%");
        return df.format(aiService.getWinRate());
    }
}
