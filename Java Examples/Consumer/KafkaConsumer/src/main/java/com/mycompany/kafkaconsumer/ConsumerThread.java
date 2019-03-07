package com.mycompany.kafkaconsumer;

import java.util.Arrays;
import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;

public class ConsumerThread extends Thread{
    
    private String topicName;
    private String groupId;
    
    private KafkaConsumer<String, String> consumer;
    
    public ConsumerThread(String topicName, String groupId){
        this.topicName = topicName;
        this.groupId = groupId;
    }
    
    public void run(){
        
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArrayDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "testClient");
        
        consumer = new KafkaConsumer<String, String>(properties);
        consumer.subscribe(Arrays.asList(topicName));
        
        try {
            ConsumerRecords<String, String> consumerRecords = null;
            while(true){
                consumerRecords = consumer.poll(100);
                for(ConsumerRecord<String, String> consumerRecord : consumerRecords){
                    System.out.println("Message from producer : " + consumerRecord.value());
                    if(consumerRecord.value().equalsIgnoreCase("raise arbitrage")){
                        Properties producerProps = new Properties();
                        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
                        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.ByteArraySerializer");
                        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
                        
                        Producer producer = new KafkaProducer(producerProps);
                        
                        ProducerRecord<String, String> producerRecord = new ProducerRecord<String,String>("test1", "Client wants to raise an arbitrage");
                        producer.send(producerRecord);
                    }
                }
            }
        } catch(WakeupException e){
            System.out.println("Wakeup exception caught");
        }
        finally {
            consumer.close();
            System.out.println("Exiting consumer");
        }
        
    }
    
    public KafkaConsumer<String, String> getInstance(){
        return this.consumer;
    }
    
}
