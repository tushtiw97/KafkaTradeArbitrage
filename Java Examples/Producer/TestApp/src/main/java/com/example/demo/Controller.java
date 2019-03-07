package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@Autowired
	ServiceClass serviceClass;
	
	@RequestMapping("/")
	public String index() {
		return "Home Page";
	}
	
	@RequestMapping("/runProducer")
	public String run() {
		try {
			serviceClass.runProducer();
			return "Producer starter. Please use command line to send messages";
		}
		catch(Exception e) {
			return "Error in starting producer: " + e.getMessage();
		}
	}
	
}
