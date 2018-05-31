package com.yash.tcvm.factoryimpl;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.yash.tcvm.exception.BeverageDoesNotExistException;
import com.yash.tcvm.exception.ConfigurationFileNotFoundException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.factory.BeverageFactory;
import com.yash.tcvm.model.Beverage;

public class BeverageFactoryImplTest {
	
	private BeverageFactory beverageFactory;
	
	@Before
	public void setUp() {
		beverageFactory = new BeverageFactoryImpl();
	}

	@Test(expected = NullValueNotAllowedException.class)
	public void createBeverage_when_nameOFBeveragePassedIsNull_should_throw_Exception() {
		String beverageName = null;
		beverageFactory.createBeverage(beverageName);
	}
	
	@Test(expected = BeverageDoesNotExistException.class)
	public void createBeverage_when_nameOFBeveragePassedIsNotNull_should_throw_Exception() {
		String beverageName = "coldCoffee";
		beverageFactory.createBeverage(beverageName);
	}
	
	@Test
	public void createBeverage_when_nameOFBeveragePassedIsNotNull_And_BeverageNameExist_should_Return_BeverageWithGivenName() {
		String beverageName = "blackcoffee";
		Beverage beverageWithGivenName = beverageFactory.createBeverage(beverageName);
		assertEquals(beverageName, beverageWithGivenName.getBeverageName());
	}
	
	
	@Test(expected = ConfigurationFileNotFoundException.class)
	public void beverageFactory_when_objectIsCreated_ByGivingWrongFileName_shouldThrow_Exception() {
		String InCorrectfileName = "Beverage.json";
		beverageFactory = new BeverageFactoryImpl(InCorrectfileName); 	
		}
	
	@Test(expected = NullValueNotAllowedException.class)
	public void beverageFactory_when_objectIsCreated_FileNameGivenIs_Null_shouldThrow_Exception() {
		String NullFileName = null;
		beverageFactory = new BeverageFactoryImpl(NullFileName); 	
		}
	
	@Test()
	public void beverageFactory_when_objectIsCreated_ByGivingRightFileName_shouldInitializeBeverageList_withSize() {
		String CorrectfileName = "Beverages.json";
		beverageFactory = new BeverageFactoryImpl(CorrectfileName); 
		assertEquals(beverageFactory.getBeverageListSize(), 4);
		}
			
	

}
