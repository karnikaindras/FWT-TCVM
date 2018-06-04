package com.yash.tcvm.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.model.TotalSalesReport;
import com.yash.tcvm.service.ReportService;

public class ReportServiceImpl implements ReportService {
	private OrderDAO orderDAO;
	
	public ReportServiceImpl(OrderDAO orderDAO) {
		this.orderDAO = orderDAO;
	}

	@Override
	public TotalSalesReport generateTotalSalesReport() {
		List<Order> orderList = orderDAO.getAllOrders();
		TotalSalesReport totalSalesReport = new TotalSalesReport();
		int totalCups = 0;
		int totalRevenue = 0;
		for (Order order : orderList) {
			if(order.getOrderType()== OrderType.ORDER) {
				totalCups++;
				totalRevenue = totalRevenue + order.getTotalAmount();
			}
		}
		totalSalesReport.setTotalCupSold(totalCups);
		totalSalesReport.setTotalRevenue(totalRevenue);
		return totalSalesReport;	
	}

	@Override
	public List<DrinkWiseReport> generateDrinkWiseReport() {
		List<Order> orderList = orderDAO.getAllOrders();
		List<DrinkWiseReport> drinkWiseReport = new ArrayList<DrinkWiseReport>();
		int totalCupsForTea =0;
		int totalRevenueForTea =0;
	
		int totalCupsForBlackTea =0;
		int totalRevenueForBlackTea =0;
		
		int totalCupsForCoffee =0;
		int totalRevenueForCoffee =0;
		
		int totalCupsForBlackCoffee =0;
		int totalRevenueForBlackCoffee =0;
		
		for (Order order : orderList) {
			if(order.getOrderType() == OrderType.ORDER) {
				
				String beverageName = order.getBeverageName();
				
				if(beverageName.equals("tea")) {
					totalCupsForTea = totalCupsForTea + order.getItemQty();
					totalRevenueForTea = totalRevenueForTea + order.getTotalAmount();
				}
				if(beverageName.equals("blacktea")) {
					totalCupsForBlackTea = totalCupsForBlackTea + order.getItemQty();
					totalRevenueForBlackTea = totalRevenueForBlackTea + order.getTotalAmount();
				}
				if(beverageName.equals("coffee")) {
					totalCupsForCoffee = totalCupsForCoffee + order.getItemQty();
					totalRevenueForCoffee = totalRevenueForCoffee + order.getTotalAmount();
				}
				if(beverageName.equals("blackcoffee")) {
					totalCupsForBlackCoffee = totalCupsForBlackCoffee + order.getItemQty();
					totalRevenueForBlackCoffee = totalRevenueForBlackCoffee + order.getTotalAmount();
				}
			}			
		}
		DrinkWiseReport reportForTea = new DrinkWiseReport("tea", totalCupsForTea, totalRevenueForTea);
		DrinkWiseReport reportForBlackTea = new DrinkWiseReport("blacktea", totalCupsForBlackTea, totalRevenueForBlackTea);
		DrinkWiseReport reportForCoffee = new DrinkWiseReport("coffee", totalCupsForCoffee, totalRevenueForCoffee);
		DrinkWiseReport reportForBlackCoffee = new DrinkWiseReport("blackcoffee", totalCupsForBlackCoffee, totalRevenueForBlackCoffee);
		drinkWiseReport.add(reportForTea);
		drinkWiseReport.add(reportForBlackTea);
		drinkWiseReport.add(reportForCoffee);
		drinkWiseReport.add(reportForBlackCoffee);

		return drinkWiseReport;
	}

	@Override
	public int generateRefillCount() {
		int refillCount = 0;
		List<Order> orderList = orderDAO.getAllOrders();
		for (Order order : orderList) {
			if(order.getOrderType() == OrderType.REFILL) {
				refillCount++;
			}
		}
		return refillCount;
	}

}
