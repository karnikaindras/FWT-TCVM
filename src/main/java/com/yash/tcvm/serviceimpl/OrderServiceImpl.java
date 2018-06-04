package com.yash.tcvm.serviceimpl;



import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.dao.OrderDAO;
import com.yash.tcvm.daoimpl.ContainerDAOImpl;
import com.yash.tcvm.daoimpl.OrderDAOImpl;
import com.yash.tcvm.exception.ContainerOverflowException;
import com.yash.tcvm.exception.InvalidInputException;
import com.yash.tcvm.exception.NotEnoughMaterialPresentException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.factory.BeverageFactory;
import com.yash.tcvm.factoryimpl.BeverageFactoryImpl;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Beverage;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.MaterialUsage;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.model.TotalSalesReport;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;
import com.yash.tcvm.service.ReportService;

public class OrderServiceImpl implements OrderService {
	
	private ContainerDAO containerDAO;
	private BeverageFactory beverageFactory;
	private ContainerService containerService;
	private ReportService reportService;
	private OrderDAO orderDAO;
	
	public OrderServiceImpl() {
		containerDAO = new ContainerDAOImpl();
		beverageFactory = new BeverageFactoryImpl();
		containerService = new ContainerServiceImpl(containerDAO);
		orderDAO = new OrderDAOImpl();
		reportService = new ReportServiceImpl(orderDAO);
	}
	public OrderServiceImpl(ContainerDAO containerDAO, BeverageFactory beverageFactory, ContainerService containerService ) {
		this.containerDAO = containerDAO;
		this.beverageFactory = beverageFactory;
		this.containerService = containerService;
	}

	

	@Override
	public boolean takeOrder(Order order) {
		boolean isOrderApproved = false;
		if(order == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_ORDER_OBJECT_IS_NULL);
		}
		if(order.getOrderType() == OrderType.ORDER) {
			String beverageName = order.getBeverageName();
			Beverage orderedBeverage = beverageFactory.createBeverage(beverageName);
			int quantity = order.getItemQty();
			if(quantity == 0) {
				throw new InvalidInputException(ExceptionMessage.WHEN_ORDER_QUANTITY_IS_ZERO);
			}
			if(quantity <0) {
				throw new InvalidInputException(ExceptionMessage.WHEN_ORDER_QUANTITY_IS_LESS_THAN_ZERO);
			}
			List<MaterialUsage> materialUsedForMakingOneBeverage = orderedBeverage.getIngredients();
			List<MaterialUsage> materialUsedForGivenBeverageQty = calculateMaterialRequiredForGivenQty(orderedBeverage, quantity,materialUsedForMakingOneBeverage );
			isOrderApproved = approveOrder(materialUsedForGivenBeverageQty);
		}
		
		return isOrderApproved;
	}

	private List<MaterialUsage> calculateMaterialRequiredForGivenQty(Beverage orderedBeverage, int quantity, List<MaterialUsage> materialUsedForMakingOneBeverage ) {
		List<MaterialUsage> materialUsedForGivenBeverageQty = null;
		
		for (MaterialUsage materialUsage : materialUsedForMakingOneBeverage) {
			materialUsage.setConsumptionQty( materialUsage.getConsumptionQty() * quantity );
			materialUsage.setWastageQty( materialUsage.getWastageQty() * quantity );
		}
		materialUsedForGivenBeverageQty = materialUsedForMakingOneBeverage;
		return materialUsedForGivenBeverageQty;
	}

	private boolean approveOrder(List<MaterialUsage> materialUsedForGivenBeverageQty) {
		boolean isOrderApproved = true;
		
		for (MaterialUsage materialUsage : materialUsedForGivenBeverageQty) {
			String materialName = materialUsage.getMaterialName();
			Container container = containerDAO.getContainerByName(materialName);
			if(container.getVolumeFilled() < (materialUsage.getConsumptionQty() + materialUsage.getWastageQty())) {
				throw new NotEnoughMaterialPresentException(ExceptionMessage.WHEN_NOT_ENOUGH_MATERIALS_ARE_PRESENT);
			}
		}
		return isOrderApproved;	
	}

	@Override
	public int generateBill(Order order) {
		if(order == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_ORDER_OBJECT_IS_NULL);
		}
		int totalBillAmount = 0;
		String beverageName = order.getBeverageName();
		int quantity = order.getItemQty();
		Beverage orderedBeverage = beverageFactory.createBeverage(beverageName);
		totalBillAmount = orderedBeverage.getPrice() * quantity;
		order.setTotalAmount(totalBillAmount);
		orderDAO.addOrder(order);
		return totalBillAmount;
	}

	@Override
	public boolean prepareOrder(Order order) {
		if(order == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_ORDER_OBJECT_IS_NULL);
		}
		
		String beverageName = order.getBeverageName();
		Beverage orderedBeverage = beverageFactory.createBeverage(beverageName);
		int quantity = order.getItemQty();
		List<MaterialUsage> materialUsedForMakingOneBeverage = orderedBeverage.getIngredients();
		List<MaterialUsage> materialUsedForGivenBeverageQty = calculateMaterialRequiredForGivenQty(orderedBeverage, quantity,materialUsedForMakingOneBeverage );
		return containerService.prepareBeverage(materialUsedForGivenBeverageQty);
	}
	
	@Override
	public List<Container> getContainerStatus() {
		return containerDAO.listContainers();
	}
	
	@Override
	public boolean refillContainer(String containerName, int refillQuantity) {
		
		if(containerName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_CONTAINER_NAME_IS_NULL);
		}
		if(refillQuantity <0) {
			throw new InvalidInputException(ExceptionMessage.WHEN_REFILL_QUANTITY_IS_NEGATIVE);
		}
		
		Container toBeRefilledContainer = containerDAO.getContainerByName(containerName);
		int maximumRefillQuantity = calculateMaximumRefillQuantity(toBeRefilledContainer);
		
		if( maximumRefillQuantity == 0) {
			throw new ContainerOverflowException(ExceptionMessage.WHEN_MAXIMUM_REFILL_QUANTITY_OF_CONTAINER_IS_ZERO);
		}
		
		if(refillQuantity > maximumRefillQuantity) {
			throw new ContainerOverflowException(ExceptionMessage.WHEN_CONTAINER_REFILL_QUANTITY_IS_MORE_THAN_CAPACITY_OF_CONATINER);
		}
		
		int volumeFilledAfterRefill = toBeRefilledContainer.getVolumeFilled() + refillQuantity;
		toBeRefilledContainer.setVolumeFilled(volumeFilledAfterRefill);
		boolean isRefilled = containerDAO.updateContainer(toBeRefilledContainer);
		return isRefilled;
	}
	
	public boolean refillContainer(Order order) {
		String containerName = order.getIngredientName();
		int refillQuantity = order.getItemQty();
		if(containerName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_CONTAINER_NAME_IS_NULL);
		}
		if(refillQuantity <0) {
			throw new InvalidInputException(ExceptionMessage.WHEN_REFILL_QUANTITY_IS_NEGATIVE);
		}
		
		Container toBeRefilledContainer = containerDAO.getContainerByName(containerName);
		int maximumRefillQuantity = calculateMaximumRefillQuantity(toBeRefilledContainer);
		
		if( maximumRefillQuantity == 0) {
			throw new ContainerOverflowException(ExceptionMessage.WHEN_MAXIMUM_REFILL_QUANTITY_OF_CONTAINER_IS_ZERO);
		}
		
		if(refillQuantity > maximumRefillQuantity) {
			throw new ContainerOverflowException(ExceptionMessage.WHEN_CONTAINER_REFILL_QUANTITY_IS_MORE_THAN_CAPACITY_OF_CONATINER);
		}
		
		int volumeFilledAfterRefill = toBeRefilledContainer.getVolumeFilled() + refillQuantity;
		toBeRefilledContainer.setVolumeFilled(volumeFilledAfterRefill);
		boolean isRefilled = containerDAO.updateContainer(toBeRefilledContainer);
		if(isRefilled) {
			orderDAO.addOrder(order);
		}
		return isRefilled;
	}
	
	private int calculateMaximumRefillQuantity(Container toBeRefilledContainer) {
		
		int maximumRefillQuantity = toBeRefilledContainer.getMaxCapacity() - toBeRefilledContainer.getVolumeFilled();
		return maximumRefillQuantity;
	}
	
	@Override
	public TotalSalesReport generateTotalSaleReport() {
		
		return reportService.generateTotalSalesReport();
	}
	@Override
	public List<DrinkWiseReport> generateDrinkWiseSaleReport() {
		return reportService.generateDrinkWiseReport();
	}
	@Override
	public int generateRefillCount() {
		return reportService.generateRefillCount();
	}
	
	

}
