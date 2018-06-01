package com.yash.tcvm.messages;

public interface ExceptionMessage {

	String WHEN_BEVERAGE_NAME_IS_NULL = "Beverage name cannot be null";
	
	String WHEN_FILE_NAME_IS_NULL = " File name cannot be null";
	
	String WHEN_CONTAINER_NAME_IS_NULL = "Container name cannot be null";
	
	String WHEN_ORDER_OBJECT_IS_NULL = "Order object cannot be null";
	
	String WHEN_MATERIALS_REQUIRED_FOR_ORDER_IS_NULL = "Required Ingredients for the beverage cannot be null";
	
	String WHEN_CONTAINER_OBJECT_PASSED_IS_NULL = "Container object cannot be null";
	
	String WHEN_MATERIAL_NAME_PASSED_IS_NULL = "Ingredient Name cannot be null";
	
	String WHEN_MATERIAL_OBJECT_PASSED_IS_NULL = "Material Object cannot be null";

	String WHEN_GIVEN_BEVERAGE_NAME_IS_INCORRECT = "Given beverage name is incorrect";
	
	String WHEN_FILE_NAME_GIVEN_IS_WRONG = "Given file name might be incorrect or file to fetch beverage configuration is not present";
	
	String WHEN_NOT_ENOUGH_MATERIALS_ARE_PRESENT = "Order cannot be processed. Not enough material Present. check container status and refill!";
	
	String WHEN_ORDER_QUANTITY_IS_ZERO = "Order quantity cannot be 0";
	
	String WHEN_LIST_OF_MATERIALS_REQUIRED_IS_EMPTY = "List of materials required cannot be empty";

	

	
	
}
