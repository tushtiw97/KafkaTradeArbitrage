package com.example.demo;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceClass {
	
	@Autowired
	Producer producer;
	
	@Autowired
	KafkaConsumer<String, String> consumer;
	
	public void runProducer() {
		Scanner input = new Scanner(System.in);
		
		consumer.subscribe(Arrays.asList("test1"));
		
		System.out.println("Enter Message : ");
		String message = input.nextLine();
		while(!message.equalsIgnoreCase("exit")) {
			ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", message);
			producer.send(producerRecord);
			
			ConsumerRecords<String, String> consumerRecords = consumer.poll(1000);
			for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
				System.out.println(consumerRecord.value());
			}
			
			System.out.println("Enter Message : ");
			message = input.nextLine();
		}
		
		try {
			consumer.wakeup();
		}
		catch(WakeupException e) {
			System.out.println("Closed consumer");
		}
		input.close();
		producer.close();
	}
	
}
