package com.yash.tcvm.controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.exception.InvalidInputException;
import com.yash.tcvm.exception.NotEnoughMaterialPresentException;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.model.TotalSalesReport;
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
					"1. Make Coffee \n2. Make Tea \n3. Make Black Coffee \n4. Make Black Tea \n5. Container Status \n6. Refill Container \n7. Reports \n0. Exit \n");
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
				
			case 6:
				order.setOrderType(OrderType.REFILL);
				refillContainerMenu();
				break;
				
			case 7:
				order.setOrderType(OrderType.REPORT);
				displayReportMenu();
				break;
				
			case 0:
				System.out.println("\nThank you for Using Tea Coffee Vending Machine.\n");
				System.exit(0);
				break;
			default:
				System.out.println("Poor choice for option. Please Select Valid Option \n");
				break;
			}
		} while (true);

		
	}

	private void displayReportMenu() {
		do {
			System.out.println("Enter your choice for generating report");
			System.out.println(" 1. Total Sale Report \n2. Drinkwise Total Sale Report  \n3 Refill Count \n0 Go Back to main menu");
			int choice = operatorInput.nextInt();
			
			switch (choice) {
			case 1:
				displayTotalSaleReport();
				break;
			
			case 2:
				displayDrinkwiseSaleReport();
				break;
			
			case 3:
				displayRefillReport();
				break;
				
			case 0:
				operatorInput.reset();
				displayMenu();
				break;
			default:
				System.out.println("Poor choice for option. Please Select Valid Option \n");
				break;
			}
		}while (true);
		
	}

	private void displayRefillReport() {
		System.out.println("*******************************Container Refill Count *********************");
		int refillCount = orderService.generateRefillCount();
		System.out.println("Refill Count" +refillCount);
	}

	private void displayDrinkwiseSaleReport() {
		List<DrinkWiseReport> drinkWiseReport = orderService.generateDrinkWiseSaleReport();
		System.out.println("*******************************Drink Wise Sale Report ****************");
		for (DrinkWiseReport report : drinkWiseReport) {
			System.out.println(report);
			System.out.println("   ----------    ");
		}
		System.out.println("*******************************Drink Wise Sale Report ****************");
		System.out.println("");
	}

	private void displayTotalSaleReport() {
		
		TotalSalesReport totalSaleReport = orderService.generateTotalSaleReport();
		System.out.println("");
		System.out.println("*****************Total Sale Report********************");
		System.out.println("Total Number of Drinks sold :- " +totalSaleReport.getTotalCupSold());
		System.out.println("Total Revenue Generated :- " +totalSaleReport.getTotalRevenue());
		System.out.println("");
		System.out.println("*****************Total Sale Report********************");
		System.out.println("");
	}

	private void refillContainerMenu() {
		do {
		System.out.println("Enter your choice to refill container");
		System.out.println(
				"1. Tea Container \n2. Coffee Container  \n3. Sugar Container \n4. Water Container \n5. Milk Container \n0. Go back to main menu \n");
		int choice = operatorInput.nextInt();
		Order order = new Order();
		order.setOrderType(OrderType.REFILL);
		switch (choice) {
		case 1:
			order.setIngredientName("tea");
			break;
		
		case 2:
			order.setIngredientName("coffee");
			break;
		
		case 3:
			order.setIngredientName("sugar");
			break;
		
		case 4:
			order.setIngredientName("water");
			break;
		
		case 5:
			order.setIngredientName("milk");
			break;
			
		case 0:
			operatorInput.reset();
			displayMenu();
			break;
		
		default:
			System.out.println("Poor choice for option. Please Select Valid Option \n");
			break;
		}
		System.out.println("Enter the refill amount for "+order.getIngredientName()+" container");
		int refillQty = operatorInput.nextInt();
		order.setItemQty(refillQty);
		refillContainer(order);
	} while (true);

		
	}

	private void refillContainer(Order order) {
		boolean isContainerRefilled = false;
		try {
			isContainerRefilled = orderService.refillContainer(order);
		}
		catch(InvalidInputException invalidInput) {
			System.out.println(invalidInput.getMessage());
		}
		catch(ContainerOverflowException containerOverflow) {
			System.out.println(containerOverflow.getMessage());
		}
		if(isContainerRefilled) {
			System.out.println("");
			checkContainerStatus();
		}
		
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
				System.out.println("total Amount payable  " +totalAmountForTheOrder);
				System.out.println("***** Cash Memo ******* " );
				System.out.println("");
			}
			
		}
		
	}

	}
