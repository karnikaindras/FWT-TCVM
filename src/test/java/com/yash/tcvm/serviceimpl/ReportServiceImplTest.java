package com.yash.tcvm.serviceimpl;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.service.ReportService;

public class ReportServiceImplTest {
	private ReportService reportService;
	
	private OrderDAO orderDAO;
	
	@Before
	public void setUp() {
		orderDAO = mock(OrderDAO.class);
		reportService = new ReportServiceImpl(orderDAO);
	}
	
	@Test
	public void generateRefillCount_WhenDAOReturnsListOfOrdersHaving2OrderTypeOrders_shouldReturn2() {
		List<Order> orderList = new ArrayList<Order>();
		Order order = new Order(1, OrderType.REFILL, "tea", 10, 1);
		Order order2 = new Order(2, OrderType.REFILL, "coffee", 10, 1);
		orderList.add(order);
		orderList.add(order2);
		when(orderDAO.getAllOrders()).thenReturn(orderList);
		int refillCount = reportService.generateRefillCount();
		assertEquals(2, refillCount);
		
	}
	
	@Test
	public void test2() {
		List<DrinkWiseReport> drinkWiseSaleReport = reportService.generateDrinkWiseReport();
		
	}

}
