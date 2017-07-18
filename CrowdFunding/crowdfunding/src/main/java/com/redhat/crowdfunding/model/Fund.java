package com.redhat.crowdfunding.model;

/**
 * @author littleredhat
 */
public class Fund {

	// 众筹地址
	private String owner;
	// 已筹人数
	private int number;
	// 已筹金币
	private int coin;

	public Fund() {
	}

	public Fund(String owner, int number, int coin) {
		this.owner = owner;
		this.number = number;
		this.coin = coin;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getCoin() {
		return coin;
	}

	public void setCoin(int coin) {
		this.coin = coin;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	@Override
	public String toString() {
		return "Fund [owner=" + owner + ", number=" + number + ", coin=" + coin + "]";
	}
}
