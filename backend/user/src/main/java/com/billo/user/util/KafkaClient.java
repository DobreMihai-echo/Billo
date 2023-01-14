package com.billo.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

//@Component
//public class KafkaClient {
//
//    //TODO: Maybe change the message to a MessageObject in order to make it more ismple
//    private final KafkaTemplate<String, String> kafkaTemplate;
//
//    @Autowired
//    public KafkaClient(KafkaTemplate<String, String> kafkaTemplate) {
//        this.kafkaTemplate = kafkaTemplate;
//    }
//
//    public void send(String topic, String message) {
//        kafkaTemplate.send(topic,message);
//    }
//}
