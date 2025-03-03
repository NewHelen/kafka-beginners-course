package io.conduktor.demos.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {

    //add logging
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello world");

        // create Producer Properties
        Properties properties = new Properties();

        // connect to localhost
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");

        // set producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        // create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // create a producer record
        ProducerRecord<String,String> producerRecord = new ProducerRecord<>("demo_java","hello world");

        // create a topic demo_java
        // kafka-topics.sh --bootstrap-server localhost:9092 --topic demo_java --create --partitions 3 --replication-factor 1

        // send data
        producer.send(producerRecord);

        // tell the producer to send all data and block until done
        producer.flush();

        // flush and close the producer
        producer.close();
    }
}
