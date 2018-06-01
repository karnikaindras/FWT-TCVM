package com.yash.tcvm.daoimpl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.exception.ConfigurationFileNotFoundException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.model.Container;


public class ContainerDAOImplTest {
	
	private ContainerDAO containerDAO;

	@Before
	public void setUp() {
		containerDAO = new ContainerDAOImpl();
	}

	@Test
	public void getContainerListSize_ContainerDAOImpl_ObjectIsCreated_shouldReturn_containerList_WithSizeEqualTo5_() {
		int containerListSize = containerDAO.getContainerListSize();
		assertEquals(5, containerListSize);
	}
	
	@Test
	public void getContainerList_ContainerDAOImpl_ObjectIsCreated_shouldReturn_containerList_WithSizeEqualTo5_() {
		List<Container> containerList = containerDAO.listContainers();
		assertEquals(5, containerList.size());
	}
	
	@Test(expected = ConfigurationFileNotFoundException.class)
	public void ContainerDAOImpl_when_objectIsCreated_ByGivingIncorrectFileName_shouldThrow_Exception() {
		String InCorrectfileName = "Container.json";
		containerDAO = new ContainerDAOImpl(InCorrectfileName);  	
		}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void getContainerByName_when_ContainerListPassed_IsNULL_shouldThrowException() {
		String containerName = null;
		containerDAO.getContainerByName(containerName);
		}
	
	@Test()
	public void getContainerByName_when_materialName_DoesNotExist_shouldReturnNull() {
		String materialName = "butter" ;
		Container container = containerDAO.getContainerByName(materialName);
		assertEquals(null, container);
		}
	
	@Test()
	public void getContainerByName_when_materialName_Exists_shouldReturnContainerObjectWithGivenName() {
		String materialName = "sugar" ;
		Container container = containerDAO.getContainerByName(materialName);
		assertEquals(materialName, container.getMaterialName());
		}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void updateContainer_whenContainerObjectPassedIsNull_shouldThrowException() {
		containerDAO.updateContainer(null);
	}
	
	@Test
	public void updateContainer_whenContainerObjectPassedIsNotNull_And_ContainerExist_shouldReturnTrue() {
		Container teaContainer = new Container("tea", 10, false, 6);
		assertTrue(containerDAO.updateContainer(teaContainer));
	}
}
