package com.yash.tcvm.model;

public class DrinkWiseReport {
	private String beverageName;
	private int totalCupsSold;
	private int totalTurnout;
	
	public String getBeverageName() {
		return beverageName;
	}
	public DrinkWiseReport(String beverageName, int totalCupsSold, int totalTurnout) {
		super();
		this.beverageName = beverageName;
		this.totalCupsSold = totalCupsSold;
		this.totalTurnout = totalTurnout;
	}
	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}
	public int getTotalCupsSold() {
		return totalCupsSold;
	}
	public void setTotalCupsSold(int totalCupsSold) {
		this.totalCupsSold = totalCupsSold;
	}
	public int getTotalTurnout() {
		return totalTurnout;
	}
	public void setTotalTurnout(int totalTurnout) {
		this.totalTurnout = totalTurnout;
	}
	@Override
	public String toString() {
		return " beverageName=" + beverageName + "\n totalCupsSold=" + totalCupsSold + "\n totalTurnout="
				+ totalTurnout + "";
	}
	
	

}
