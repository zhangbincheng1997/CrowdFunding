package com.redhat.crowdfunding.model;

/**
 * @author littleredhat
 */
public class Fund {

    // 项目编号
    private int fundIndex;

    // 众筹地址
    private String owner;
    // 众筹描述
    private String desc;
    // 众筹目标
    private int goal;

    // 已筹金币
    private int coins;
    // 是否结束
    private boolean finished;

    public Fund() {
    }

    public Fund(int fundIndex, String owner, String desc, int goal, int coins, boolean finished) {
        this.fundIndex = fundIndex;
        this.owner = owner;
        this.desc = desc;
        this.goal = goal;
        this.coins = coins;
        this.finished = finished;
    }

    public int getFundIndex() {
        return fundIndex;
    }

    public void setFundIndex(int fundIndex) {
        this.fundIndex = fundIndex;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundIndex=" + fundIndex +
                ", owner='" + owner + '\'' +
                ", desc='" + desc + '\'' +
                ", goal=" + goal +
                ", coins=" + coins +
                ", finished=" + finished +
                '}';
    }
}