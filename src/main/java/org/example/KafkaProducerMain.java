
package org.example;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Collections;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.IntStream;

class KafkaProducerMain {
    public static void main(String[] args) {
        Properties consumerProperties = new Properties();
        consumerProperties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        consumerProperties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        consumerProperties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        consumerProperties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, UUID.randomUUID().toString());


        KafkaProducer<String, String> producer = new KafkaProducer<>(consumerProperties);
        producer.initTransactions();

        producer.beginTransaction();
        IntStream.range(0, 10).mapToObj(t->new ProducerRecord<String,String>("test",t+" from finished Producer")).forEach(t-> producer.send(t));
        producer.flush();
        producer.commitTransaction();
        producer.close();
    }
}

