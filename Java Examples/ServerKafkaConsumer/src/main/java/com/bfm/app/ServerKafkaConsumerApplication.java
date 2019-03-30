package com.bfm.app;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class ServerKafkaConsumerApplication implements CommandLineRunner{

	@Autowired
	private KafkaConsumer<String, String> consumer;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public static void main(String[] args) {
		SpringApplication.run(ServerKafkaConsumerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		consumer.subscribe(Arrays.asList("test1"));
		ConsumerRecords<String, String> consumerRecords = null;
		ConsumerRecord<String, String> consumerRecord = null;
		try {
			
			while(true) {
				consumerRecords = consumer.poll(100);
				for(ConsumerRecord<String, String> currentConsumerRecord : consumerRecords) {
					consumerRecord = currentConsumerRecord;
					try {
						JSONObject jsonObject = new JSONObject(consumerRecord.value());
						String orderTime = jsonObject.getString("orderTime");
						String securityCode = jsonObject.getString("securityCode");
						String buyMkt = jsonObject.getString("buyMkt");
						Integer buyPrice = jsonObject.getInt("buyPrice");
						String sellMkt = jsonObject.getString("sellMkt");
						Integer sellPrice = jsonObject.getInt("sellPrice");
						Integer orderQty = jsonObject.getInt("orderQty");
						System.out.println("Order Time : " + orderTime + " | Security Code : " + securityCode + " | Buy Market : " + buyMkt + " | Buy Price : " + buyPrice + " | Sell Market : " + sellMkt + " Sell Price : " + sellPrice + " Order Quantity : " + orderQty);					
						jdbcTemplate.update("insert into arbitrage values (?)", consumerRecord.value());
						System.out.println("Inserted");
					} catch (JSONException e) {
						System.out.println("Could not insert");
						consumer.wakeup();
					} catch (Exception e) {
						System.out.println("Could not insert");
					}
					System.out.println(consumerRecord.value());
				}
			}
		} catch(WakeupException e) {
			System.out.println("Stopping consumer");
		}
		consumer.close();
	}

}
