package com.yash.tcvm.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;

public class OrderDAOImplTest {
	private OrderDAO orderDAO;
	
	@Before 
	public void setUp() {
		orderDAO = new OrderDAOImpl();
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void addOrder_OrderObjectPassedIsNull_shouldThrowException() {
		Order order = null;
		orderDAO.addOrder(order);	
	}
	
	@Test()
	public void addOrder_OrderObjectPassedIsNotNull_shouldReturnTrue() {
		Order order = new Order(1,OrderType.ORDER, "coffee", 10, 2);
		orderDAO.addOrder(order);	
	}
	
	@Test()
	public void getAllOrders_whenTwoNonNullOrderObjectsAreAdded_shouldReturnAListWithSize2() {
		Order order1 = new Order(1,OrderType.ORDER, "coffee", 15, 2);
		Order order2 = new Order(2,OrderType.ORDER, "tea", 15, 2);
		orderDAO.addOrder(order1);
		orderDAO.addOrder(order2);
		List<Order> listOrders =orderDAO.getAllOrders();
		assertEquals(2, listOrders.size());
		
	}

	
}
