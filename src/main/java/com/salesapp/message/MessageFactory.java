package com.salesapp.message;

import com.salesapp.constant.AppConstant;

public class MessageFactory {
	
	public Message createMessage(String message) throws Exception{
		
		String[]  msgTokens = message.split(" ");
		
		if(msgTokens.length == AppConstant.NORMAL_ADJUSTMENT_SALE_TOKEN){
			String firstToken = msgTokens[0];
			if(firstToken.equals(AppConstant.ADD) || firstToken.equals(AppConstant.MULTIPLY) || firstToken.equals(AppConstant.SUBTRACT)){
				return createAdjustmentSaleMessage(msgTokens);
			}else{
				return createNormalSaleMessage(msgTokens);
			}
		}else if(msgTokens.length == AppConstant.MULTIPLE_SALE_TOKEN){
			return createMultipleSaleMessage(msgTokens);
		}else{
			throw new Exception("Message.createMessage(String message): Unsupported message  => " + message);
		}
		
	}
	
	public Message createMultipleSaleMessage(String[] tokenArray){
		MultipleSale multipleSale = new MultipleSale();
		String quantity = tokenArray[0];
		String name = tokenArray[3];
		String unitPrice = tokenArray[5];
		multipleSale.setName(name.substring(0, name.lastIndexOf('s')));
		multipleSale.setQuantity(Integer.parseInt(quantity));
		multipleSale.setmUnit(unitPrice.charAt(unitPrice.indexOf('p')));
		multipleSale.setUnitPrice(Integer.parseInt(unitPrice.substring(0, unitPrice.indexOf('p'))));
		return multipleSale;
	}
	
	public Message createAdjustmentSaleMessage(String[] tokenArray){
		AdjustmentSale adjustmentSale = new AdjustmentSale();
		String operation = tokenArray[0];
		String amount = tokenArray[1];
		String name = tokenArray[2];
		adjustmentSale.setName(name.substring(0, name.lastIndexOf('s')));
		adjustmentSale.setOperation(operation);
		adjustmentSale.setmUnit(amount.charAt(amount.indexOf('p')));
		adjustmentSale.setAmount(Integer.parseInt(amount.substring(0, amount.indexOf('p'))));
		return adjustmentSale;
	}
	
	public Message createNormalSaleMessage(String[] tokenArray){
		NormalSale normalSale = new NormalSale();
		String name = tokenArray[0];
		String unitPrice = tokenArray[2];
		normalSale.setName(name);
		normalSale.setmUnit(unitPrice.charAt(unitPrice.indexOf('p')));
		normalSale.setPrice(Integer.parseInt(unitPrice.substring(0, unitPrice.indexOf('p'))));
		return normalSale;
	}
}
