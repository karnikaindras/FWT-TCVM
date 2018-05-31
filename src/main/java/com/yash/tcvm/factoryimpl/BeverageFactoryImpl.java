package com.yash.tcvm.factoryimpl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yash.tcvm.exception.BeverageDoesNotExistException;
import com.yash.tcvm.exception.ConfigurationFileNotFoundException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.factory.BeverageFactory;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Beverage;

public class BeverageFactoryImpl implements BeverageFactory {
	private List<Beverage> beverageList;
	
	private Gson gson;

	public BeverageFactoryImpl() {
		gson = new Gson();
		beverageList = getBeverageList();
	}
	
	public BeverageFactoryImpl(String fileName) {
		gson = new Gson();
		beverageList = getBeverageList(fileName);
	}

	private List<Beverage> getBeverageList(String fileName) {
		BufferedReader br;
		List<Beverage> beverageList = new ArrayList<Beverage>();
		
		if( fileName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_FILE_NAME_IS_NULL);
		}
	
			try {
				br = new BufferedReader(
					     new FileReader(fileName));
			} catch (FileNotFoundException e) {
				throw new ConfigurationFileNotFoundException(ExceptionMessage.WHEN_FILE_NAME_GIVEN_IS_WRONG);
			}
			beverageList = gson.fromJson(br, new TypeToken<List<Beverage>>(){}.getType());
		
		return beverageList;
	}

	private List<Beverage> getBeverageList() {
		BufferedReader br;
		List<Beverage> beverageList = new ArrayList<Beverage>();
		try {
			br = new BufferedReader(
				     new FileReader("Beverages.json"));
			beverageList = gson.fromJson(br, new TypeToken<List<Beverage>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return beverageList;
	}
	
	public int getBeverageListSize() {
		return beverageList.size();
	}

	@Override
	public Beverage createBeverage(String beverageName) {
		Beverage beverageWithGivenName;
		if(beverageName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_BEVERAGE_NAME_IS_NULL);
		}
		beverageWithGivenName = findBeverageWithGivenName(beverageName);
		return beverageWithGivenName;
	}

	private Beverage findBeverageWithGivenName(String beverageName) {
		Beverage beverageWithGivenName = null;
		for (Beverage beverage : beverageList) {
			if(beverage.getBeverageName().equals( beverageName)) {
				beverageWithGivenName =beverage;
			}
		}
		if(beverageWithGivenName == null) {
			throw new BeverageDoesNotExistException(ExceptionMessage.WHEN_GIVEN_BEVERAGE_NAME_IS_INCORRECT);
		}
		return beverageWithGivenName;
	}

}
