package com.bfm.app.controller;

import java.util.List;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bfm.app.model.Person;
import com.bfm.app.service.ServiceClass;

@RestController
public class Controller {
	
	@Autowired
	private ServiceClass serviceClass;
	
	@RequestMapping("/")
	public String index() {
		return "This is the home page. Go to <a href='arbitrage.html'>Arbitrage</a>";
	}
	
	@RequestMapping("/getArbitrage")
	public String getArbitrage() {
		return serviceClass.getArbitrages();
	}
	
}
