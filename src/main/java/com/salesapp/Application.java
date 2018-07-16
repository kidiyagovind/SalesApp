package com.salesapp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

import com.salesapp.constant.AppConstant;
import com.salesapp.message.Message;
import com.salesapp.message.MessageFactory;
import com.salesapp.provider.ArrayMessageProvider;
import com.salesapp.provider.MessageProvider;
import com.salesapp.sale.AdjustmentType;
import com.salesapp.sale.Sale;

public class Application {

    static int totalValue = 0;

    public static void main(String[] args) throws InterruptedException {
    	Map<String, ArrayList<Sale>> salesStore = new Hashtable<String, ArrayList<Sale>>();
		MessageProvider msgProvider = new ArrayMessageProvider();
        for(int i = 1; i <= AppConstant.MAX_MSG_THRESHOLD_COUNT; i++){
        	Message msg = getNextMessage(msgProvider.getMessageStr());
        	ArrayList<Sale> sales = msg.createSale();
        	processSales(sales, salesStore);
        	if(i % AppConstant.REPORT_GENERATION_COUNT == 0){
        		System.out.println("Intermediate Sales Report after "+ AppConstant.REPORT_GENERATION_COUNT + "messages....");
        		printSalesReport(salesStore);
        	}
        }
        System.out.println("SalesMessageProcessor application is pausing.......");
        printAdjustmentReport(salesStore);
        Thread.sleep(AppConstant.THREAD_SLEEP_TIME);
    }
    
    public static Message getNextMessage(String msg){
    	MessageFactory msgFactory = new MessageFactory();
    	Message message = null;
    	try{
    		message = msgFactory.createMessage(msg);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    	return message;
    }
    
    public static void printAdjustmentReport(Map<String, ArrayList<Sale>> salesStore){
    	salesStore.forEach((key, value) -> {
    		value.forEach(sale->{
    			AdjustmentType adjustmentType = sale.getValue().getAdjustment().getAdjustment();
    			int adjustmentAmt = sale.getValue().getAdjustment().getAdjustmentAmt();
    			if(adjustmentType != AdjustmentType.NONE){
    				System.out.println("Adjustment to " + key + " : " + adjustmentType + " " + adjustmentAmt + "p.");
    			}
    		});
    	    
    	});
    }
    
    public static void printSalesReport(Map<String, ArrayList<Sale>> salesStore){
    	salesStore.forEach((key, value) -> {
    		int size = value.size();
    		totalValue = 0;
    		value.forEach(sale->{
    			totalValue += sale.getValue().getValue();
    		});
    	    System.out.println(size + " " + key + "s"+ " sold for " + totalValue + "p.");
    	});
    }
    
    public static void processSales(ArrayList<Sale> sales, Map<String, ArrayList<Sale>> salesStore){
    	for(Sale sale: sales){
    		if(!salesStore.containsKey(sale.getProductType())){
    			ArrayList<Sale> newSaleType = new ArrayList<Sale>();
				newSaleType.add(sale);
				salesStore.put(sale.getProductType(), newSaleType);
    		}else{
    			ArrayList<Sale> storedSales = salesStore.get(sale.getProductType());
    			if(sale.getValue().getAdjustment().getAdjustment() != AdjustmentType.NONE){
        			for(Sale storedSale: storedSales){
        				if(storedSale.getValue().getAdjustment().getAdjustment() == AdjustmentType.NONE){
        					storedSale.adjustValue(sale.getValue().getAdjustment());
        				}
        			}
        		}
    			storedSales.add(sale);
    			salesStore.replace(sale.getProductType(), storedSales);
    		}
    	}    	
    }
    
}
