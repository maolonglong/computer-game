package com.csl.game;

import com.csl.core.mcts.BaseAction;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author MaoLongLong
 * @date 2021-02-18 21:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class TicTacToeMove extends BaseAction {

    private final int x;

    private final int y;

    private final int value;
}
