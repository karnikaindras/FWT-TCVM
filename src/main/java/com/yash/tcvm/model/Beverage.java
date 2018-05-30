package com.yash.tcvm.model;

import java.util.List;

public class Beverage {
	
	private String beverageName;
	
	private int price;
	
	private List<MaterialUsage> ingredients;
	
	public Beverage() {

	}

	public Beverage(String beverageName, int price, List<MaterialUsage> ingredients) {
		super();
		this.beverageName = beverageName;
		this.price = price;
		this.ingredients = ingredients;
	}

	public String getBeverageName() {
		return beverageName;
	}

	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public List<MaterialUsage> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<MaterialUsage> ingredients) {
		this.ingredients = ingredients;
	}
	
	

}
