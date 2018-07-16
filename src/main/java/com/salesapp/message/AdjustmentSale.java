package com.salesapp.message;

import java.util.ArrayList;
import com.salesapp.sale.Adjustment;
import com.salesapp.sale.AdjustmentType;
import com.salesapp.sale.Sale;
import com.salesapp.sale.SaleValue;

public class AdjustmentSale extends Message {

	private String operation;
	private int amount;
	
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "AdjustmentSale [ name=" + getName() + ", mUnit=" + getmUnit() + ", operation=" + operation + ", amount=" + amount + "]";
	}
	@Override
	public ArrayList<Sale> createSale() {
		ArrayList<Sale> sales = new ArrayList<Sale>();
		Sale sale = new Sale();
		sale.setProductType(getName());
		SaleValue value = new SaleValue();
		value.setValue(0);
		value.setmUnit(getmUnit());
		Adjustment adjustment = new Adjustment();
		if(operation.equals("Multiply")){
			adjustment.setAdjustment(AdjustmentType.MULTIPLY);
		}else if(operation.equals("Add")){
			adjustment.setAdjustment(AdjustmentType.ADD);
		}else if(operation.equals("Substract")){
			adjustment.setAdjustment(AdjustmentType.SUBSTRACT);
		}
		adjustment.setmUnit(getmUnit());
		adjustment.setAdjustmentAmt(getAmount());
		value.setAdjustment(adjustment);
		sale.setValue(value);
		sales.add(sale);
		return sales;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + amount;
		result = prime * result + ((operation == null) ? 0 : operation.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		AdjustmentSale other = (AdjustmentSale) obj;
		if (amount != other.amount)
			return false;
		if (operation == null) {
			if (other.operation != null)
				return false;
		} else if (!operation.equals(other.operation))
			return false;
		return true;
	}
	
}
