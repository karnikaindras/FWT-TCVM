package com.yash.tcvm.model;

public class Container {
	
	private String materialName;
	
	private int maxCapacity;
	
	private boolean IsEmpty;
	
	private int volumeFilled;
	
	

	public Container() {
	
	}

	public Container(String materialName, int maxCapacity, boolean isEmpty, int volumeFilled) {
		super();
		this.materialName = materialName;
		this.maxCapacity = maxCapacity;
		IsEmpty = isEmpty;
		this.volumeFilled = volumeFilled;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public int getMaxCapacity() {
		return maxCapacity;
	}

	public void setMaxCapacity(int maxCapacity) {
		this.maxCapacity = maxCapacity;
	}

	public boolean isIsEmpty() {
		return IsEmpty;
	}

	public void setIsEmpty(boolean isEmpty) {
		IsEmpty = isEmpty;
	}

	public int getVolumeFilled() {
		return volumeFilled;
	}

	public void setVolumeFilled(int volumeFilled) {
		this.volumeFilled = volumeFilled;
	}
	
	

}
