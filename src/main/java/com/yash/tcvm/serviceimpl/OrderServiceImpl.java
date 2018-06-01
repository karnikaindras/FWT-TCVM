package com.yash.tcvm.serviceimpl;



import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.exception.InvalidInputException;
import com.yash.tcvm.exception.NotEnoughMaterialPresentException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.factory.BeverageFactory;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Beverage;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.MaterialUsage;
import com.yash.tcvm.model.Order;
import com.yash.tcvm.model.OrderType;
import com.yash.tcvm.service.ContainerService;
import com.yash.tcvm.service.OrderService;

public class OrderServiceImpl implements OrderService {
	
	private ContainerDAO containerDAO;
	private BeverageFactory beverageFactory;
	private ContainerService containerService;
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

}
