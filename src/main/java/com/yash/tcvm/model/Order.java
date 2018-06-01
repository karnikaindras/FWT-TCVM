package com.yash.tcvm.model;

public class Order {
	
	private int orderId;
	private OrderType orderType;
	private String beverageName;
	private int price;
	private int itemQty;
	
	public Order(int orderId, OrderType orderType, String itemName, int price, int itemQty) {
		super();
		this.orderId = orderId;
		this.orderType = orderType;
		this.beverageName = itemName;
		this.price = price;
		this.itemQty = itemQty;
	}
	
	public Order() {
		
	}

	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public OrderType getOrderType() {
		return orderType;
	}
	public void setOrderType(OrderType orderType) {
		this.orderType = orderType;
	}
	public String getBeverageName() {
		return beverageName;
	}
	public void setBeverageName(String itemName) {
		this.beverageName = itemName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getItemQty() {
		return itemQty;
	}
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	
	
}
