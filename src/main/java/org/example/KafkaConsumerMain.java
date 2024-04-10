package org.example;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

class KafkaConsumerMain {
     public static void main(String[] args) throws InterruptedException {
         Properties consumerProperties = new Properties();
         consumerProperties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
         consumerProperties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
         consumerProperties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
         consumerProperties.put(ConsumerConfig.GROUP_ID_CONFIG, UUID.randomUUID().toString());
         consumerProperties.put(ConsumerConfig.ISOLATION_LEVEL_CONFIG, "read_committed");
//         consumerProperties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 290000);
//         consumerProperties.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 15000);

         KafkaConsumer<String, String> consumer = new KafkaConsumer<>(consumerProperties);
         consumer.subscribe(Collections.singletonList("test"));
         while(true){

             ConsumerRecords<String, String> poll = consumer.poll(Duration.ofSeconds(5));
             for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                 String value = stringStringConsumerRecord.value();
                 System.out.println(LocalDateTime.now()+" "+value);
             }

         }

     }
 }

