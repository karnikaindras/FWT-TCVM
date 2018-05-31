package com.yash.tcvm.model;

public class MaterialUsage {
	
	private String materialName;
	
	private int consumptionQty;
	
	private String materialMeasurementUnit;
	
	private int wastageQty;
	
	public MaterialUsage() {
	
	}

	public MaterialUsage(String materialName, int consumptionQty, String materialMeasurementUnit, int wastageQty) {
		super();
		this.materialName = materialName;
		this.consumptionQty = consumptionQty;
		this.materialMeasurementUnit = materialMeasurementUnit;
		this.wastageQty = wastageQty;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public int getConsumptionQty() {
		return consumptionQty;
	}

	public void setConsumptionQty(int consumptionQty) {
		this.consumptionQty = consumptionQty;
	}

	public String getMaterialMeasurementUnit() {
		return materialMeasurementUnit;
	}

	public void setMaterialMeasurementUnit(String materialMeasurementUnit) {
		this.materialMeasurementUnit = materialMeasurementUnit;
	}

	public int getWastageQty() {
		return wastageQty;
	}

	public void setWastageQty(int wastageQty) {
		this.wastageQty = wastageQty;
	}
	
	@Override
	public String toString() {
		return "MaterialUsage [materialName=" + materialName + ", consumptionQty=" + consumptionQty
				+ ", materialMeasurementUnit=" + materialMeasurementUnit + ", wastageQty=" + wastageQty + "]";
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
		MaterialUsage other = (MaterialUsage) obj;
		if (materialName == null) {
			if (other.materialName != null)
				return false;
		} else if (!materialName.equals(other.materialName))
			return false;
		return true;
	}

}
