package com.bfm.app.configuration;

import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public KafkaConsumer<String, String> consumer(){
		Properties properties = new Properties();
		
		properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
		properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
		properties.put(ConsumerConfig.GROUP_ID_CONFIG, "0");
		properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "ServerConsumer");
		
		return new KafkaConsumer<String, String>(properties);
	}
	
}
