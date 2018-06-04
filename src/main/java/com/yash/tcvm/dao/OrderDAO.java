package com.yash.tcvm.dao;

import java.util.List;

import com.yash.tcvm.model.Order;

public interface OrderDAO {

	boolean addOrder(Order order);

	List<Order> getAllOrders();

}
