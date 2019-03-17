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
				jsonObject.put("nseBuyPrice", random.nextInt(100) + 300);
				jsonObject.put("nseSellPrice", random.nextInt(100) + 300);
				jsonObject.put("bseBuyPrice", random.nextInt(100) + 300);
				jsonObject.put("bseSellPrice", random.nextInt(100) + 300);
			}
			else {
				Integer buy = random.nextInt(100) + 300;
				Integer sell = random.nextInt(100) + 300;
				jsonObject.put("nseBuyPrice", buy);
				jsonObject.put("nseSellPrice", sell);
				jsonObject.put("bseBuyPrice", buy);
				jsonObject.put("bseSellPrice", sell);
			}
			
			jsonArray.put(jsonObject);
			
		}
		
		return jsonArray;
		
	}
	
}
