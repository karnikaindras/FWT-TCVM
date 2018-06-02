package com.yash.tcvm.serviceimpl;


import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.exception.InvalidInputException;
import com.yash.tcvm.exception.NotEnoughMaterialPresentException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.factory.BeverageFactory;
import com.yash.tcvm.model.Beverage;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.MaterialUsage;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;

public class OrderServiceImplTest {
	private OrderService orderService;
	Beverage blacktea = null;
	Container teaContainer = null;
	Container waterContainer = null;
	Container sugarContainer = null;
	Container underFlowTeaContainer = null;
	List<Container> containerList;
	
	private ContainerDAO containerDAO;
	private ContainerService containerService;
	private BeverageFactory beverageFactory;
	
	@Before
	public void setUp() {
		containerDAO = mock(ContainerDAO.class);
		beverageFactory = mock(BeverageFactory.class);
		containerService = mock(ContainerService.class);
		orderService = new OrderServiceImpl(containerDAO, beverageFactory, containerService);
		
		List<MaterialUsage> materialUsageForBeverage = new ArrayList<MaterialUsage>();
		MaterialUsage tea = new MaterialUsage("tea", 3, "gm", 0);
		MaterialUsage water = new MaterialUsage("water", 100, "gm", 12);
		MaterialUsage sugar = new MaterialUsage("sugar", 15, "gm", 2);
		materialUsageForBeverage.add(tea);
		materialUsageForBeverage.add(water);
		materialUsageForBeverage.add(sugar);
		
		blacktea = new Beverage("blacktea", 5, materialUsageForBeverage);
		
		teaContainer = new Container("tea", 10, false, 10);
		underFlowTeaContainer = new Container("tea", 3, false, 3);
		waterContainer = new Container("water", 250, false, 250);
		sugarContainer = new Container("sugar", 40, false, 40);
		
		containerList = new ArrayList<Container>();
		containerList.add(teaContainer);
		containerList.add(waterContainer);
	}

	@Test(expected = NullValueNotAllowedException.class)
	public void takeOrderMethod_WhenNullOrderObjectIsPassed_shouldThrowException() {
		Order order = null;
		orderService.takeOrder(order);
	}
	
	@Test(expected = InvalidInputException.class)
	public void takeOrderMethod_WhenOrderObjectPassed_IsNotNull_But_ItemQuantityIsZero_shouldThrowException() {
		Order order = new Order(1, OrderType.ORDER, "blacktea",0,0); 
		orderService.takeOrder(order);
	}
	
	@Test(expected = InvalidInputException.class)
	public void takeOrderMethod_WhenOrderObjectPassed_IsNotNull_But_ItemQuantityIsLessThanZero_shouldThrowException() {
		Order order = new Order(1, OrderType.ORDER, "blacktea",0, -1); 
		orderService.takeOrder(order);
	}
	
	@Test
	public void takeOrderMethod_WhenOrderTypeIsOrder_AndSufficientMaterialIsPresent_shouldReturnTrue() {
		Order order = new Order(1, OrderType.ORDER, "blacktea",0,2); 
		when(beverageFactory.createBeverage(any(String.class))).thenReturn(blacktea);
		when(containerDAO.getContainerByName("tea")).thenReturn(teaContainer);
		when(containerDAO.getContainerByName("water")).thenReturn(waterContainer);
		when(containerDAO.getContainerByName("sugar")).thenReturn(sugarContainer);
		boolean isOrderTaken = orderService.takeOrder(order);
		assertTrue(isOrderTaken);
	}
	

	@Test(expected = NotEnoughMaterialPresentException.class)
	public void takeOrderMethod_WhenOrderTypeIsOrder_AndSufficientMaterialIsNotPresent_shouldThrowException() {
		Order order = new Order(1, OrderType.ORDER, "blacktea",0,2); 
		when(beverageFactory.createBeverage(any(String.class))).thenReturn(blacktea);
		when(containerDAO.getContainerByName("tea")).thenReturn(underFlowTeaContainer);
		when(containerDAO.getContainerByName("water")).thenReturn(waterContainer);
		when(containerDAO.getContainerByName("sugar")).thenReturn(sugarContainer);
		orderService.takeOrder(order);
	}
	
	@Test
	public void generateBill_shouldReturnTotalBillAmount_Equal_To_10() {
		Order order = new Order(1, OrderType.ORDER, "blacktea",0,2);
		when(beverageFactory.createBeverage("blacktea")).thenReturn(blacktea);
		int totalAmount = orderService.generateBill(order);
		assertEquals(10, totalAmount);
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void generateBill_WhenOrderObjectPassedIs_NULL_ShouldThrowException() {
		Order order = null;
		orderService.generateBill(order);
		
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void prepareOrder_WhenOrderObjectPassedIs_NULL_ShouldThrowException() {
		Order order = null;
		orderService.prepareOrder(order);
		
	}
	
	@Test
	public void getContainerStatus_shouldReturn_NonEmptyListOfContainers() {
		when(containerDAO.listContainers()).thenReturn(containerList);
		List<Container> containerStatusList = orderService.getContainerStatus();
		assertFalse(containerStatusList.isEmpty());
	}
	

}
