package com.salesapp.message;

import java.util.ArrayList;

import com.salesapp.sale.Adjustment;
import com.salesapp.sale.AdjustmentType;
import com.salesapp.sale.Sale;
import com.salesapp.sale.SaleValue;

public class NormalSale extends Message {
	private int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "NormalSale [name=" + getName() + ", mUnit=" + getmUnit() + ", price=" + price + "]";
	}

	@Override
	public ArrayList<Sale> createSale() {
		ArrayList<Sale> sales = new ArrayList<Sale>();
		Sale sale = new Sale();
		sale.setProductType(getName());
		SaleValue value = new SaleValue();
		value.setValue(getPrice());
		value.setmUnit(getmUnit());
		Adjustment adjustment = new Adjustment();
		adjustment.setAdjustment(AdjustmentType.NONE);
		adjustment.setmUnit(getmUnit());
		adjustment.setAdjustmentAmt(0);
		value.setAdjustment(adjustment);
		sale.setValue(value);
		sales.add(sale);
		return sales;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + price;
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
		NormalSale other = (NormalSale) obj;
		if (price != other.price)
			return false;
		return true;
	}
	
}
