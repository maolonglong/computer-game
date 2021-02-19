package com.csl.core.mcts;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author MaoLongLong
 * @date 2021-02-18 18:00
 */
public class MonteCarloTreeNode {

    /**
     * 访问次数
     */
    private int visits;

    /**
     * 博弈结果
     */
    private final Map<Integer, Integer> resultMap;

    /**
     * 当前棋盘状态
     */
    private final BaseState state;

    public BaseState getState() {
        return state;
    }

    /**
     * 父节点
     */
    private final MonteCarloTreeNode parent;

    /**
     * 子节点
     */
    private final List<MonteCarloTreeNode> children;

    /**
     * 未尝试的移动
     */
    private final List<? extends BaseAction> untriedActions;

    public MonteCarloTreeNode(BaseState state, MonteCarloTreeNode parent) {
        resultMap = CollUtil.newHashMap();
        children = CollUtil.newArrayList();
        this.state = state;
        this.parent = parent;
        this.untriedActions = state.getLegalActions();
    }

    /**
     * 因为输赢是相对的，所以得到当前的玩家是哪一方，再计算得分
     *
     * @return 当前节点得分
     */
    protected double score() {
        int currentPlayer = parent.state.getNextMove();
        int wins = resultMap.getOrDefault(currentPlayer, 0);
        int loses = resultMap.getOrDefault(-1 * currentPlayer, 0);
        return wins - loses;
    }

    public double winRate() {
        int currentPlayer = parent.state.getNextMove();
        int loses = resultMap.getOrDefault(-1 * currentPlayer, 0);
        return (double) (visits - loses) / visits;
    }

    /**
     * 扩展当前节点
     *
     * @return 子节点
     */
    public MonteCarloTreeNode expand() {
        BaseAction action = untriedActions.remove(untriedActions.size() - 1);
        BaseState nextState = (BaseState) state.move(action);
        MonteCarloTreeNode childNode = new MonteCarloTreeNode(nextState, this);
        children.add(childNode);
        return childNode;
    }

    /**
     * @return 是否为终止节点(叶子)
     */
    public boolean isTerminalNode() {
        return state.isGameOver();
    }

    /**
     * 模拟一局博弈
     *
     * @return 博弈结果
     */
    public int rollOut() {
        BaseState tempState = ObjectUtil.cloneByStream(state);
        while (!tempState.isGameOver()) {
            List<? extends BaseAction> actions = tempState.getLegalActions();
            BaseAction action = rollOutPlicy(actions);
            tempState = (BaseState) tempState.move(action);
        }
        return tempState.getGameResult();
    }

    /**
     * 规定选择动作的策略，默认为随机选择
     *
     * @param actions 可能的动作集
     * @return 最终选择的动作
     */
    protected BaseAction rollOutPlicy(List<? extends BaseAction> actions) {
        return RandomUtil.randomEle(actions);
    }

    /**
     * 回溯记录游戏结果
     *
     * @param res 游戏结果
     */
    public void back(int res) {
        visits++;
        resultMap.put(res, resultMap.getOrDefault(res, 0) + 1);
        if (Objects.nonNull(parent)) {
            parent.back(res);
        }
    }

    public boolean isFullyExpanded() {
        return untriedActions.size() == 0;
    }

    public MonteCarloTreeNode bestChild() {
        return bestChild(1.44);
    }

    public MonteCarloTreeNode bestChild(double p) {
        List<Double> weights = children.stream()
                .map(c -> (c.score() / c.visits) + p * Math.sqrt(2 * Math.log(visits) / c.visits))
                .collect(Collectors.toList());

        double maxWeight = -0x3f3f3f3f;
        int index = 0;
        for (int i = 0; i < weights.size(); i++) {
            if (weights.get(i) > maxWeight) {
                maxWeight = weights.get(i);
                index = i;
            }
        }
        return children.get(index);
    }
}
