package com.yash.tcvm.service;

import java.util.List;

import com.yash.tcvm.model.MaterialUsage;

public interface ContainerService {

	boolean prepareBeverage(List<MaterialUsage> materialUsedForGivenBeverageQty);

}
