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
import com.yash.tcvm.exception.EmptyCollectionException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.MaterialUsage;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImplTest {
	private ContainerService containerService;
	
	private ContainerDAO containerDAO;
	
	
	private Container teaContainer;
	private Container waterContainer;
	private Container sugarContainer;
	private List<MaterialUsage> materialUsageForBlackTea;
	
	@Before
	public void setUp() {
		containerDAO = mock(ContainerDAO.class);
		containerService = new ContainerServiceImpl(containerDAO);
		materialUsageForBlackTea = new ArrayList<MaterialUsage>();
		MaterialUsage tea = new MaterialUsage("tea", 3, "gm", 0);
		MaterialUsage water = new MaterialUsage("water", 100, "gm", 12);
		MaterialUsage sugar = new MaterialUsage("sugar", 15, "gm", 2);
		materialUsageForBlackTea.add(tea);
		materialUsageForBlackTea.add(water);
		materialUsageForBlackTea.add(sugar);
		
		teaContainer = new Container("tea", 10, false, 10);
		waterContainer = new Container("water", 200, false, 200);
		sugarContainer = new Container("sugar", 20, false, 20);
		
	}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void prepareBeverage_When_ListPassed_IsNull_ShouldThrowException() {
		containerService.prepareBeverage(null);
	}
	
	@Test(expected = EmptyCollectionException.class)
	public void prepareBeverage_When_ListPassed_IsEmpty_ShouldThrowException() {
		List<MaterialUsage> ingredientsForBeverage = new ArrayList<MaterialUsage>();
		containerService.prepareBeverage(ingredientsForBeverage);
	}
	
	@Test
	public void prepareBeverage_When_ListPassed_IsNonEmpty_AndAllMaterialsExist_ShouldReturntrue() {
		when(containerDAO.getContainerByName("tea")).thenReturn(teaContainer);
		when(containerDAO.getContainerByName("water")).thenReturn(waterContainer);
		when(containerDAO.getContainerByName("sugar")).thenReturn(sugarContainer);
		when(containerDAO.updateContainer(any(Container.class))).thenReturn(true);
		boolean result = containerService.prepareBeverage(materialUsageForBlackTea);
		assertTrue(result);
	}


}
