package com.salesapp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.salesapp.message.AdjustmentSale;
import com.salesapp.message.MultipleSale;
import com.salesapp.message.NormalSale;
import com.salesapp.sale.Adjustment;
import com.salesapp.sale.AdjustmentType;
import com.salesapp.sale.Sale;
import com.salesapp.sale.SaleValue;

public class SaleCreationTest {

	@Test
	public void performNormalSaleTest() {
		NormalSale inputMsg = new NormalSale();
		inputMsg.setName("apple");
		inputMsg.setmUnit('p');
		inputMsg.setPrice(10);
		
		Sale expectedSale = new Sale();
		expectedSale.setProductType("apple");
		SaleValue expectedValue = new SaleValue();
		Adjustment expectedAdjustment = new Adjustment();
		expectedAdjustment.setAdjustment(AdjustmentType.NONE);
		expectedAdjustment.setAdjustmentAmt(0);
		expectedAdjustment.setmUnit('p');
		expectedValue.setAdjustment(expectedAdjustment);
		expectedValue.setmUnit('p');
		expectedValue.setValue(10);
		expectedSale.setValue(expectedValue);
		
		ArrayList<Sale> actualSale = inputMsg.createSale();
		
		assertEquals(expectedSale, actualSale.get(0));
	}
	
	@Test
	public void performMultipleSaleTest() {
		MultipleSale inputMsg = new MultipleSale();
		inputMsg.setName("banana");
		inputMsg.setmUnit('p');
		inputMsg.setQuantity(9);
		inputMsg.setUnitPrice(22);
		
		Sale expectedSale = new Sale();
		expectedSale.setProductType("banana");
		SaleValue expectedValue = new SaleValue();
		Adjustment expectedAdjustment = new Adjustment();
		expectedAdjustment.setAdjustment(AdjustmentType.NONE);
		expectedAdjustment.setAdjustmentAmt(0);
		expectedAdjustment.setmUnit('p');
		expectedValue.setAdjustment(expectedAdjustment);
		expectedValue.setmUnit('p');
		expectedValue.setValue(22);
		expectedSale.setValue(expectedValue);
		
		ArrayList<Sale> actualSale = inputMsg.createSale();
		
		assertEquals(inputMsg.getQuantity(), actualSale.size());
		assertEquals(expectedSale, actualSale.get(0));
	}
	
	@Test
	public void performAdjustmentSaleTest() {
		AdjustmentSale inputMsg = new AdjustmentSale();
		inputMsg.setName("apple");
		inputMsg.setmUnit('p');
		inputMsg.setOperation("Multiply");
		inputMsg.setAmount(2);
		
		Sale expectedSale = new Sale();
		expectedSale.setProductType("apple");
		SaleValue expectedValue = new SaleValue();
		Adjustment expectedAdjustment = new Adjustment();
		expectedAdjustment.setAdjustment(AdjustmentType.MULTIPLY);
		expectedAdjustment.setAdjustmentAmt(2);
		expectedAdjustment.setmUnit('p');
		expectedValue.setAdjustment(expectedAdjustment);
		expectedValue.setmUnit('p');
		expectedValue.setValue(0);
		expectedSale.setValue(expectedValue);
		
		ArrayList<Sale> actualSale = inputMsg.createSale();
		
		assertEquals(expectedSale, actualSale.get(0));
	}

}
