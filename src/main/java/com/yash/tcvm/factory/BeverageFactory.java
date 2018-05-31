package com.yash.tcvm.factory;

import com.yash.tcvm.model.Beverage;

public interface BeverageFactory {

	public Beverage createBeverage(String beverageName);
	
	public int getBeverageListSize();

}
