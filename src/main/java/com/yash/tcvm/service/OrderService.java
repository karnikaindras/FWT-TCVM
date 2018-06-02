package com.yash.tcvm.service;

import java.util.List;

import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;

public interface OrderService {

	boolean takeOrder(Order order);

	int generateBill(Order order);

	boolean prepareOrder(Order order);

	List<Container> getContainerStatus();

}
