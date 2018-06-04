package com.yash.tcvm.service;

import java.util.List;

import com.yash.tcvm.model.DrinkWiseReport;
import com.yash.tcvm.model.TotalSalesReport;

public interface ReportService {

	TotalSalesReport generateTotalSalesReport();

	List<DrinkWiseReport> generateDrinkWiseReport();

	int generateRefillCount();

}
