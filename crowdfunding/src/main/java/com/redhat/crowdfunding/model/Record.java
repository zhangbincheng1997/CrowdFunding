package com.redhat.crowdfunding.model;

/**
 * @author littleredhat
 */
public class Record {

    // 捐赠成员
    private String member;
    // 捐赠金币
    private int coin;
    // 捐赠时间
    private int time;

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getCoin() {
        return coin;
    }

    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Record{" +
                "member='" + member + '\'' +
                ", coin=" + coin +
                ", time=" + time +
                '}';
    }
}