package com.bfm.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.bfm.app.datagenerator.DataGenerator;

@SpringBootApplication
public class KafkaProducerApplication implements CommandLineRunner{

	@Autowired
	private Producer producer;
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		for(int i=0;i<10;i++) {
			System.out.println(i);
		}
		
		System.out.println("Enter your name : ");
		Scanner input = new Scanner(System.in);
		String name = input.nextLine();
		System.out.println("Name is : " + name);
		
//		System.out.println("Enter message : ");
//		String message = input.nextLine();
//		
//		ProducerRecord<String, String> producerRecord = null;
//		
//		while(true) {
//			producerRecord = new ProducerRecord<String, String>("test", message);
//			producer.send(producerRecord);
//			
//			System.out.println("Enter message : ");
//			message = input.nextLine();
//			
//			if(message.equalsIgnoreCase("exit")) {
//				producerRecord = new ProducerRecord<String, String>("test", message);
//				producer.send(producerRecord);
//				break;
//			}
//		}
		
		DataGenerator dataGenerator = new DataGenerator();
		
		JSONArray jsonArray = dataGenerator.getJsonArray();
		
		for(int i = 0; i < jsonArray.length(); i++) {
			if(i%7 == 0) {
				Random random = new Random();
				try {
					Thread.sleep(5000 + (random.nextInt(5) * 1000));
				} catch (Exception e) {
					System.out.println("Cannot pause thread");
				}
			}
			
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", jsonObject.toString());
			producer.send(producerRecord);
			System.out.println("Sent : " + jsonObject.toString());
		}
		
		ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", "exit");
		producer.send(producerRecord);
		
		producer.close();
		input.close();
	}

}
