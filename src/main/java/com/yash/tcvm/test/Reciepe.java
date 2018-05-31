package com.yash.tcvm.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.google.gson.Gson;
import com.yash.tcvm.model.Beverage;
import com.yash.tcvm.model.MaterialUsage;

public class Reciepe {

	public static void main(String[] args) {
		List<Beverage> beverageList = Collections.synchronizedList(new ArrayList<Beverage>());
		List<MaterialUsage> materialUsageListTea =Collections.synchronizedList(new ArrayList<MaterialUsage>());
		List<MaterialUsage> materialUsageListBlackTea =Collections.synchronizedList(new ArrayList<MaterialUsage>());
		List<MaterialUsage> materialUsageListCoffee =Collections.synchronizedList(new ArrayList<MaterialUsage>());
		List<MaterialUsage> materialUsageListBlackCoffee =Collections.synchronizedList(new ArrayList<MaterialUsage>());
		
		Beverage Tea = new Beverage();
		Tea.setBeverageName("tea");
		Tea.setPrice(10);
		MaterialUsage Teatea = new  MaterialUsage("tea", 5, "gm", 1);
		MaterialUsage Teawater = new  MaterialUsage("water", 60, "ml", 5);
		MaterialUsage Teamilk = new  MaterialUsage("milk", 40, "gm", 4);
		MaterialUsage Teasugar = new  MaterialUsage("sugar", 15, "gm", 2);
		materialUsageListTea.add(Teatea);
		materialUsageListTea.add(Teawater);
		materialUsageListTea.add(Teamilk);
		materialUsageListTea.add(Teasugar);
		Tea.setIngredients(materialUsageListTea);
		beverageList.add(Tea);
		
	
		
		Beverage BlackTea = new Beverage();
		BlackTea.setBeverageName("blacktea");
		BlackTea.setPrice(5);
		MaterialUsage BlackTeatea = new  MaterialUsage("tea", 3, "gm", 0);
		MaterialUsage BlackTeawater = new  MaterialUsage("water", 100, "ml", 12);
		MaterialUsage BlackTeasugar = new  MaterialUsage("sugar", 15, "gm", 2);
		materialUsageListBlackTea.add(BlackTeatea);
		materialUsageListBlackTea.add(BlackTeawater);
		materialUsageListBlackTea.add(BlackTeasugar);
		
		BlackTea.setIngredients(materialUsageListBlackTea);
		beverageList.add(BlackTea);
		
	
		
		Beverage Coffee = new Beverage();
		Coffee.setBeverageName("coffee");
		Coffee.setPrice(15);
		MaterialUsage Coffee_coffee = new  MaterialUsage("coffee", 4, "gm", 1);
		MaterialUsage Coffee_water = new  MaterialUsage("water", 20, "ml", 3);
		MaterialUsage Coffee_milk = new  MaterialUsage("milk", 80, "gm", 8);
		MaterialUsage Coffee_sugar = new  MaterialUsage("sugar", 15, "gm", 2);
		materialUsageListCoffee.add(Coffee_coffee);
		materialUsageListCoffee.add(Coffee_water);
		materialUsageListCoffee.add(Coffee_milk);
		materialUsageListCoffee.add(Coffee_sugar);
		
		Coffee.setIngredients(materialUsageListCoffee);
		beverageList.add(Coffee);
		
	
	

		
		Beverage BlackCoffee = new Beverage();
		BlackCoffee.setBeverageName("blackcoffee");
		BlackCoffee.setPrice(10);
		
		MaterialUsage Black_Coffee_coffee = new  MaterialUsage("coffee", 3, "gm", 0);
		MaterialUsage Black_Coffee_water = new  MaterialUsage("water", 100, "ml", 12);
		MaterialUsage Black_Coffee_sugar = new  MaterialUsage("sugar", 15, "gm", 2);
		materialUsageListBlackCoffee.add(Black_Coffee_coffee);
		materialUsageListBlackCoffee.add(Black_Coffee_water);
		materialUsageListBlackCoffee.add(Black_Coffee_sugar);
		
		BlackCoffee.setIngredients(materialUsageListBlackCoffee);
		beverageList.add(BlackCoffee);
		
	
		
		Gson gson = new Gson();
		String beverageListToJson = gson.toJson(beverageList);
		
		
		try {
			FileWriter writer = new FileWriter("Beverages.json");
			writer.write(beverageListToJson);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
		
		for (Beverage beverage :beverageList ) {
			System.out.println(beverage);
		}
	}

}
