package com.yash.tcvm.serviceimpl;

import java.util.List;

import com.yash.tcvm.dao.ContainerDAO;
import com.yash.tcvm.daoimpl.ContainerDAOImpl;
import com.yash.tcvm.exception.EmptyCollectionException;
import com.yash.tcvm.exception.NullValueNotAllowedException;
import com.yash.tcvm.messages.ExceptionMessage;
import com.yash.tcvm.model.Container;
import com.yash.tcvm.model.MaterialUsage;
import com.yash.tcvm.service.ContainerService;

public class ContainerServiceImpl implements ContainerService {
	
	private ContainerDAO containerDAO;
	
	public ContainerServiceImpl() {
		containerDAO = new ContainerDAOImpl();
	}
	
	public ContainerServiceImpl(ContainerDAO containerDAO) {
		this.containerDAO = containerDAO;
	}

	@Override
	public boolean prepareBeverage(List<MaterialUsage> materialUsedForGivenBeverageQty) {
		boolean isMaterialAdded = false;
		if(materialUsedForGivenBeverageQty == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_MATERIALS_REQUIRED_FOR_ORDER_IS_NULL);
		}
		if(materialUsedForGivenBeverageQty.isEmpty()) {
			throw new EmptyCollectionException(ExceptionMessage.WHEN_LIST_OF_MATERIALS_REQUIRED_IS_EMPTY);
		}
		for (MaterialUsage material : materialUsedForGivenBeverageQty) {
			
			isMaterialAdded = addMaterialToBeverage(material);
		}
		return isMaterialAdded;
	}

	private boolean addMaterialToBeverage(MaterialUsage material) {
		if( material == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_MATERIAL_OBJECT_PASSED_IS_NULL);
		}
		Container containerForThisMaterial = getContainerByName(material.getMaterialName());
		int newContainerVolume = containerForThisMaterial.getVolumeFilled() - material.getConsumptionQty() - material.getWastageQty();
		if(newContainerVolume == 0) {
			containerForThisMaterial.setIsEmpty(true);
		}
		containerForThisMaterial.setVolumeFilled(newContainerVolume);
		return containerDAO.updateContainer(containerForThisMaterial);
	}

	private Container getContainerByName(String materialName) {
		if( materialName == null) {
			throw new NullValueNotAllowedException(ExceptionMessage.WHEN_MATERIAL_NAME_PASSED_IS_NULL);
		}
		return containerDAO.getContainerByName(materialName);
	}

}
