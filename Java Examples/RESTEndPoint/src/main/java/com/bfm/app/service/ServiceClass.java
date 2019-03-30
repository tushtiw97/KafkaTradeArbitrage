package com.bfm.app.service;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bfm.app.dao.ArbitrageDao;
import com.bfm.app.dao.PersonDao;
import com.bfm.app.model.Person;

@Service
public class ServiceClass {
	
	@Autowired
	private ArbitrageDao arbitrageDao;
	
	public String getArbitrages() {
		return arbitrageDao.getArbitrageJsonArray();
	}
	
}
