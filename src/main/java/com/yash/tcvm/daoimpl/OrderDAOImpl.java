package com.yash.tcvm.daoimpl;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Order;

public class OrderDAOImpl implements OrderDAO {
	private Gson gson;
	
	private List<Order> orderList;
	
	private FileWriter writer;
	
	public OrderDAOImpl() {
		gson = new Gson();
		orderList = new ArrayList<Order>();
	}

	@Override
	public boolean addOrder(Order order) {
		if(order == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_ORDER_OBJECT_IS_NULL);
		}
		orderList.add(order);
		saveToFile();
		return true;
	}

	private void saveToFile() {
		String screenListToJson = gson.toJson(orderList);
		try {
			writer = new FileWriter("Orders.json");
			writer.write(screenListToJson);
			writer.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<Order> getAllOrders() {
		return orderList;
	}

}
