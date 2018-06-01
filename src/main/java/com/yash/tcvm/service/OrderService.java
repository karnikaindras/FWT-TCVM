package com.yash.tcvm.service;

import com.yash.tcvm.model.Order;

public interface OrderService {

	boolean takeOrder(Order order);

	int generateBill(Order order);

	boolean prepareOrder(Order order);

}
