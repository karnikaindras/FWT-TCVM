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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((materialName == null) ? 0 : materialName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Container other = (Container) obj;
		if (materialName == null) {
			if (other.materialName != null)
				return false;
		} else if (!materialName.equals(other.materialName))
			return false;
		return true;
	}
	
	

}
