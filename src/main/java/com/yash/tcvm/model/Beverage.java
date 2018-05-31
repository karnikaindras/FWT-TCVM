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
	
	@Override
	public String toString() {
		return "Beverage [beverageName=" + beverageName + ", price=" + price + ", ingredients=" + ingredients + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((beverageName == null) ? 0 : beverageName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Beverage other = (Beverage) obj;
		if (beverageName == null) {
			if (other.beverageName != null)
				return false;
		} else if (!beverageName.equals(other.beverageName))
			return false;
		return true;
	}



}
