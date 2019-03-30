package com.bfm.app.dao;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArbitrageDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String FETCH_ARBITRAGE = "select * from arbitrage";
	
	public String getArbitrageJsonArray() {
		List<Map<String, Object>> returnedRows = jdbcTemplate.queryForList(FETCH_ARBITRAGE);
		JSONArray jsonArray = new JSONArray("[]");
		for(Map row : returnedRows) {
			JSONObject jsonObject = new JSONObject((String) row.get("jsonString"));
			jsonArray.put(jsonObject);
		}
		return jsonArray.toString();
	}
	
}
