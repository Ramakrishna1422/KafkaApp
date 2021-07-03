package com.learning.rama.kafka.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Producer {

    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        System.out.println("#### -> Producing message -> " + message);
        ProducerRecord<String, String> record = new ProducerRecord<>(TOPIC, message);
        record.headers().add(new RecordHeader(KafkaHeaders.CORRELATION_ID, UUID.randomUUID().toString().getBytes()));
        this.kafkaTemplate.send(record);
    }

    public void sendMessage(String correlationId, String topic, String message) {
        System.out.println("#### -> Producing message -> " + message);
        ProducerRecord<String, String> record = new ProducerRecord<>(topic, message);
        record.headers().add(new RecordHeader(KafkaHeaders.CORRELATION_ID, correlationId.getBytes()));
        this.kafkaTemplate.send(record);
    }
}
