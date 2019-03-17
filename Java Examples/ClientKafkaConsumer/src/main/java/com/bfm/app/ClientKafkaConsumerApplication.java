package com.bfm.app;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientKafkaConsumerApplication implements CommandLineRunner{

	@Autowired
	private KafkaConsumer<String, String> consumer;
	
	@Autowired
	private Producer producer;
	
	public static void main(String[] args) {
		SpringApplication.run(ClientKafkaConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		consumer.subscribe(Arrays.asList("test"));
		
		try {
			while(true) {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
				for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					//System.out.println("Message received : " + consumerRecord.value());
					if(consumerRecord.value().contentEquals("exit")) {
						ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test1", "exit");
						producer.send(producerRecord);
						consumer.wakeup();
					}
					else if(consumerRecord.value().contentEquals("raise arbitrage")) {
						ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test1", "client wants to raise an arbitrage");
						producer.send(producerRecord);
					}
					else {
						JSONObject jsonObject = new JSONObject(consumerRecord.value());
						System.out.println("Received " + jsonObject.toString());
						String cusip = jsonObject.getString("cusip");
						if(Integer.parseInt(cusip.split("_")[1])%4 == 0) {
							ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test1", jsonObject.toString());
							producer.send(producerRecord);
						}
					}
				}
			}
		} catch (WakeupException e) {
			System.out.println("Exception caught. Stopping consumer");
		}
		consumer.close();
		
	}

}
