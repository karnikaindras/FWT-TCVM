package com.yash.tcvm.main;

import com.yash.tcvm.controller.TcvmController;

public class StartUp {

	public static void main(String[] args) {
		
		TcvmController tcvmController = new TcvmController();
		tcvmController.displayMenu();

	}

}
