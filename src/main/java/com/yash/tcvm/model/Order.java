package com.yash.tcvm.model;

public class Order {
	static int orderIDCount =1;
	private int orderId;
	private OrderType orderType;
	private String beverageName;
	private int itemQty;
	private String ingredientName;
	private int totalAmount;
	
	
	public Order(int orderId, OrderType orderType, String itemName, int price, int itemQty) {
		super();
		this.orderId = orderIDCount++;
		this.orderType = orderType;
		this.beverageName = itemName;
		this.itemQty = itemQty;
	}
	
	public Order() {
		
	}
	
	

	public int getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
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

	public int getItemQty() {
		return itemQty;
	}
	
	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}
	
	
	
	
}
