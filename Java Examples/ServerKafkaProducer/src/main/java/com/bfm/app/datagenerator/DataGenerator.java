package com.bfm.app.datagenerator;

import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;

public class DataGenerator {
	
	private JSONArray jsonArray;
	
	public JSONArray getJsonArray() {
		
		jsonArray = new JSONArray();
		
		for(int i = 0; i < 5000; i++) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("cusip", "cusip_" + (i+1));
			jsonObject.put("nseId", "nse_" + (i+4));
			jsonObject.put("bseId", "bse_" + (i+7));
			
			Random random = new Random();
			Integer randInt = random.nextInt(5) + 5;
			
			if(i%randInt == 0) {
				
				JSONArray bseBuyPrices = new JSONArray();
				bseBuyPrices.put(random.nextInt(100) + 300);
				bseBuyPrices.put(random.nextInt(100) + 300);
				bseBuyPrices.put(random.nextInt(100) + 300);
				bseBuyPrices.put(random.nextInt(100) + 300);
				bseBuyPrices.put(random.nextInt(100) + 300);
				
				JSONArray bseSellPrices = new JSONArray();
				bseSellPrices.put(random.nextInt(100) + 300);
				bseSellPrices.put(random.nextInt(100) + 300);
				bseSellPrices.put(random.nextInt(100) + 300);
				bseSellPrices.put(random.nextInt(100) + 300);
				bseSellPrices.put(random.nextInt(100) + 300);
				
				JSONArray nseBuyPrices = new JSONArray();
				nseBuyPrices.put(random.nextInt(100) + 300);
				nseBuyPrices.put(random.nextInt(100) + 300);
				nseBuyPrices.put(random.nextInt(100) + 300);
				nseBuyPrices.put(random.nextInt(100) + 300);
				nseBuyPrices.put(random.nextInt(100) + 300);
				
				JSONArray nseSellPrices = new JSONArray();
				nseSellPrices.put(random.nextInt(100) + 300);
				nseSellPrices.put(random.nextInt(100) + 300);
				nseSellPrices.put(random.nextInt(100) + 300);
				nseSellPrices.put(random.nextInt(100) + 300);
				nseSellPrices.put(random.nextInt(100) + 300);
				
				jsonObject.put("bseBuyPrices", bseBuyPrices);
				jsonObject.put("bseSellPrices", bseSellPrices);
				jsonObject.put("nseBuyPrices", nseBuyPrices);
				jsonObject.put("nseSellPrices", bseSellPrices);
				
				JSONArray bseBuyQty = new JSONArray();
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				
				JSONArray nseBuyQty = new JSONArray();
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				
				JSONArray bseSellQty = new JSONArray();
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				
				JSONArray nseSellQty = new JSONArray();
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				
				jsonObject.put("bseBuyQty", bseBuyQty);
				jsonObject.put("bseSellQty", bseSellQty);
				jsonObject.put("nseBuyQty", nseBuyQty);
				jsonObject.put("nseSellQty", nseSellQty);
				
//				jsonObject.put("nseBuyPrice", random.nextInt(100) + 300);
//				jsonObject.put("nseSellPrice", random.nextInt(100) + 300);
//				jsonObject.put("bseBuyPrice", random.nextInt(100) + 300);
//				jsonObject.put("bseSellPrice", random.nextInt(100) + 300);
			}
			else {
				Integer buy = random.nextInt(100) + 300;
				Integer sell = random.nextInt(100) + 300;
				
				JSONArray bseBuyPrices = new JSONArray();
				bseBuyPrices.put(buy);
				bseBuyPrices.put(buy + 4);
				bseBuyPrices.put(buy + 6);
				bseBuyPrices.put(buy + 8);
				bseBuyPrices.put(buy + 10);
				
				JSONArray bseSellPrices = new JSONArray();
				bseSellPrices.put(sell);
				bseSellPrices.put(sell + 4);
				bseSellPrices.put(sell + 6);
				bseSellPrices.put(sell + 8);
				bseSellPrices.put(sell + 10);
				
				JSONArray nseBuyPrices = new JSONArray();
				nseBuyPrices.put(buy);
				nseBuyPrices.put(buy + 4);
				nseBuyPrices.put(buy + 6);
				nseBuyPrices.put(buy + 8);
				nseBuyPrices.put(buy + 10);
				
				JSONArray nseSellPrices = new JSONArray();
				nseSellPrices.put(sell);
				nseSellPrices.put(sell + 4);
				nseSellPrices.put(sell + 6);
				nseSellPrices.put(sell + 8);
				nseSellPrices.put(sell + 10);
				
				jsonObject.put("bseBuyPrices", bseBuyPrices);
				jsonObject.put("bseSellPrices", bseSellPrices);
				jsonObject.put("nseBuyPrices", nseBuyPrices);
				jsonObject.put("nseSellPrices", bseSellPrices);
				
				JSONArray bseBuyQty = new JSONArray();
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				bseBuyQty.put(random.nextInt(10) + 1);
				
				JSONArray nseBuyQty = new JSONArray();
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				nseBuyQty.put(random.nextInt(10) + 1);
				
				JSONArray bseSellQty = new JSONArray();
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				bseSellQty.put(random.nextInt(10) + 1);
				
				JSONArray nseSellQty = new JSONArray();
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				nseSellQty.put(random.nextInt(10) + 1);
				
				jsonObject.put("bseBuyQty", bseBuyQty);
				jsonObject.put("bseSellQty", bseSellQty);
				jsonObject.put("nseBuyQty", nseBuyQty);
				jsonObject.put("nseSellQty", nseSellQty);
				
//				jsonObject.put("nseBuyPrice", buy);
//				jsonObject.put("nseSellPrice", sell);
//				jsonObject.put("bseBuyPrice", buy);
//				jsonObject.put("bseSellPrice", sell);
			}
			
			jsonArray.put(jsonObject);
			
		}
		
		return jsonArray;
		
	}
	
}
