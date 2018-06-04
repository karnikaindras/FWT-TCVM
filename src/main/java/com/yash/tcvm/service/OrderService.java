package com.yash.tcvm.service;

import java.util.List;
import java.util.Map;

import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.TotalSalesReport;

public interface OrderService {

	boolean takeOrder(Order order);

	int generateBill(Order order);

	boolean prepareOrder(Order order);

	List<Container> getContainerStatus();
	
	public boolean refillContainer(Order order);

	TotalSalesReport generateTotalSaleReport();

	List<DrinkWiseReport> generateDrinkWiseSaleReport();

	int generateRefillCount();


}
