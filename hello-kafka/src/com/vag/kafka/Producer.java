package com.vag.kafka;

import java.util.Properties;
import java.util.Scanner;

import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;

public class Producer {
  private static Scanner in;
  public static void main(String[] argv)throws Exception {
      if (argv.length != 1) {
          System.err.println("Please specify 1 parameters ");
          System.exit(-1);
      }
      String topicName = argv[0];
      in = new Scanner(System.in);
      System.out.println("Enter message(type exit to quit)");

      //Configure the Producer
      Properties configProperties = new Properties();
      configProperties.put("metadata.broker.list","192.168.17.128:9092");
      configProperties.put("key.serializer.class","com.vag.kafka.Encoder");
      configProperties.put("serializer.class","com.vag.kafka.Encoder");
      configProperties.put("partitioner.class", "com.vag.kafka.SimplePartitioner");
      configProperties.put("producer.type", "sync");
      ProducerConfig producerConfig = new ProducerConfig(configProperties);
      kafka.javaapi.producer.Producer<String, String> producer = new kafka.javaapi.producer.Producer<String, String>(producerConfig);
      System.out.println(producer);
      String line = in.nextLine();
      while(!line.equals("exit")) {
          KeyedMessage<String, String> rec = new KeyedMessage<String, String>(topicName ,"nokey",line);
          producer.send(rec);
          
          line = in.nextLine();
      }
      in.close();
      producer.close();
  }
}