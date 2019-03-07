package com.mycompany.kafkaconsumer;

import java.util.Properties;
import java.util.Scanner;
import org.apache.kafka.clients.consumer.ConsumerConfig;

public class ConsumerMain {
    
    public static void main(String[] args){
        
        System.out.println("Enter topic name : ");
        Scanner in = new Scanner(System.in);
        String topicName = in.nextLine();
        
        System.out.println("Enter group id: ");
        String groupId = in.nextLine();
        
        ConsumerThread consumerThread = new ConsumerThread(topicName, groupId);
        consumerThread.start();
        
        String message = "";
        
        while(!message.equalsIgnoreCase("exit")){
            message = in.nextLine();
        }
        
        consumerThread.getInstance().wakeup();
        System.out.println("Closing consumer");
        try{
            consumerThread.join();
        } catch (InterruptedException e){
            System.out.println("Interrupted Exceptio");
        }
        
    }
    
}
