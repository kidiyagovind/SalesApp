package com.salesapp;

import static org.junit.Assert.*;

import org.junit.Test;

import com.salesapp.message.*;

public class MessageCreationTest {

	@Test
	public void nomralSaleMessageTest() {
		String inputMsg = "apple at 10p";
		NormalSale expectedMsg = new NormalSale();
		expectedMsg.setName("apple");
		expectedMsg.setmUnit('p');
		expectedMsg.setPrice(10);
		MessageFactory msgFactory = new MessageFactory();
		Message actualMsg = null;
		try{
			actualMsg = msgFactory.createMessage(inputMsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(expectedMsg, actualMsg);
	}

	@Test
	public void adjustmentSaleMessageTest() {
		String inputMsg = "Multiply 2p apples";
		AdjustmentSale expectedMsg = new AdjustmentSale();
		expectedMsg.setName("apple");
		expectedMsg.setmUnit('p');
		expectedMsg.setOperation("Multiply");
		expectedMsg.setAmount(2);
		MessageFactory msgFactory = new MessageFactory();
		Message actualMsg = null;
		try{
			actualMsg = msgFactory.createMessage(inputMsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(expectedMsg, actualMsg);
	}
	
	@Test
	public void multipleSaleMessageTest() {
		String inputMsg = "9 sales of bananas at 22p each";
		MultipleSale expectedMsg = new MultipleSale();
		expectedMsg.setName("banana");
		expectedMsg.setmUnit('p');
		expectedMsg.setQuantity(9);
		expectedMsg.setUnitPrice(22);
		MessageFactory msgFactory = new MessageFactory();
		Message actualMsg = null;
		try{
			actualMsg = msgFactory.createMessage(inputMsg);
		}catch(Exception e){
			e.printStackTrace();
		}
		assertEquals(expectedMsg, actualMsg);
	}

}
