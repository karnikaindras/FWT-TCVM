package com.yash.tcvm.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yash.tcvm.exception.InvalidInputException;
import com.yash.tcvm.exception.NotEnoughMaterialPresentException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.service.OrderService;
import com.yash.tcvm.serviceimpl.OrderServiceImpl;

public class TcvmController {
	
	private Scanner operatorInput;
	private OrderService orderService;

	public TcvmController() {
		operatorInput = new Scanner(System.in);
		orderService = new OrderServiceImpl();
	}

	public void displayMenu() {
		int choice = 0;
		do {
			System.out.println("---------------- Tea Coffee Vending Machine ---------------");
			System.out.println(
					"1. Make Coffee \n2. Make Tea \n3. Make Black Coffee \n4. Make Black Tea \n5. Container Status \n0. Exit \n");
			System.out.println("Enter Your Choice :-");
			choice = operatorInput.nextInt();
			Order order = new Order();
			switch (choice) {
			case 1:
				order.setOrderType(OrderType.ORDER);
				order.setBeverageName("coffee");
				takeOrderForBeverage(order);
				break;
			
			case 2:
				order.setOrderType(OrderType.ORDER);
				order.setBeverageName("tea");
				takeOrderForBeverage(order);
				break;
			
			case 3:
				order.setOrderType(OrderType.ORDER);
				order.setBeverageName("blackcoffee");
				takeOrderForBeverage(order);
				break;
			
			case 4:
				order.setOrderType(OrderType.ORDER);
				order.setBeverageName("blacktea");
				takeOrderForBeverage(order);
				break;
			case 5:
				order.setOrderType(OrderType.STATUS);
				checkContainerStatus();
				break;
			case 0:
				System.out.println("\nThank you for Using Movie Booking System.\n");
				System.exit(0);
				break;
			default:
				System.out.println("Poor choice for option. Please Select Valid Option \n");
				break;
			}
		} while (true);

		
	}

	private void checkContainerStatus() {
		List<Container> containerStatusList = new ArrayList<Container>();
		 containerStatusList = orderService.getContainerStatus();
		System.out.println("  Container Name     " +"   VolumeFilled");
		for (Container container : containerStatusList) {
			System.out.print("   "+container.getMaterialName()+"                     "+container.getVolumeFilled());
			System.out.println();
		}
	}

	private void takeOrderForBeverage(Order order) {
		
		System.out.println("Enter no of "+order.getBeverageName()+" ");
		
		try {
			order.setItemQty(operatorInput.nextInt());
			boolean isOrderApproved = orderService.takeOrder(order);
			if(isOrderApproved) {
				generateBill(isOrderApproved, order);
			}
			
		} catch(InputMismatchException e) {
			System.out.println("Invalid Input Order quantity can only be an integer");
		}
		catch(InvalidInputException e) {
			System.out.println(e.getMessage());
		} catch(NotEnoughMaterialPresentException e) {
			System.out.println(e.getMessage());
		} 
		
	}

	private void generateBill(boolean isOrderApproved, Order order) {
		if(isOrderApproved) {
			boolean isOrderReady = orderService.prepareOrder(order);
			if(isOrderReady) {
				System.out.println("Your order is ready");
				for(int i=0;i<order.getItemQty();i++) {
					System.out.print(" [_]? ");
				}
				System.out.println("");
				int totalAmountForTheOrder = orderService.generateBill(order);
				System.out.println("***** Cash Memo ******* " );
				System.out.print(" "+order.getBeverageName()+" "+order.getItemQty()+" ");
				System.out.println("total Amount payable" +totalAmountForTheOrder);
			}
			
		}
		
	}

	}
