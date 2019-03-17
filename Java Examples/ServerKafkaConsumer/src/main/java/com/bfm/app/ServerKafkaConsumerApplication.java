package com.bfm.app;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerKafkaConsumerApplication implements CommandLineRunner{

	@Autowired
	private KafkaConsumer<String, String> consumer;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerKafkaConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		consumer.subscribe(Arrays.asList("test1"));
		
		try {
			while(true) {
				ConsumerRecords<String, String> consumerRecords = consumer.poll(100);
				for(ConsumerRecord<String, String> consumerRecord : consumerRecords) {
					System.out.println(consumerRecord.value() + " -- Saved in database");
					if(consumerRecord.value().contentEquals("exit")) {
						consumer.wakeup();
					}
				}
			}
		}
		catch(WakeupException e) {
			System.out.println("Stopping consumer");
		}
		
		consumer.close();
		
	}

}
