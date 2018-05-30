package com.yash.tcvm.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.yash.tcvm.model.Container;

public class Test {

	public static void main(String[] args) {
		List<Container> containerList = new LinkedList<Container>();
		Container tea = new Container("tea", 2, false, 2);
		Container coffee = new Container("coffee", 2, false, 2);
		Container sugar = new Container("tea", 8, false, 8);
		Container water = new Container("tea", 15, false, 15);
		Container milk = new Container("tea", 10, false, 10);
		
		containerList.add(tea);
		containerList.add(coffee);
		containerList.add(sugar);
		containerList.add(sugar);
		containerList.add(water);
		containerList.add(milk);
		
		Gson gson = new Gson();
		String containerListToJson = gson.toJson(containerList);
		
		
//		try {
//			FileWriter writer = new FileWriter("Containers.json");
//			writer.write(containerListToJson);
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		};
		

	}

}
