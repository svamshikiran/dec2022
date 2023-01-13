package com.example.dec2022.kafka;

import com.example.dec2022.model.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.DataInput;

@Component
public class KafkaListenerService {

    @KafkaListener(topics = "student.topic", groupId = "user_group")
    public void consumeUser1(ConsumerRecord<String, Student> consumerRecord) {

        System.out.println("CONSUMER RECORD: "+consumerRecord.value());
    }
}
