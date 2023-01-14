//package com.billo.notification.config;
//
//import com.billo.clients.models.Message;
//import org.apache.kafka.clients.consumer.ConsumerConfig;
//import org.apache.kafka.clients.producer.ProducerConfig;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.kafka.common.serialization.StringSerializer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.kafka.annotation.EnableKafka;
//import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
//import org.springframework.kafka.config.KafkaListenerContainerFactory;
//import org.springframework.kafka.core.*;
//import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
//import org.springframework.kafka.support.converter.StringJsonMessageConverter;
//import org.springframework.kafka.support.serializer.JsonDeserializer;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@EnableKafka
//@Configuration
//public class KafkaConsumerConfig {
//
//    @Value("${spring.kafka.consumer.bootstrap-servers")
//    private String BOOTSTRAP_SERVERS;
//
//    @Value("${spring.kafka.consumer.group-id}")
//    private String GROUP_ID;
//
//    @Value("${spring.kafka.topic.name}")
//    private String MESSAGE_ID;
//
////    @Bean
////    public ConsumerFactory<String, String> consumerFactory() {
////        Map<String, Object> props = new HashMap<>();
////        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS);
////        props.put(ConsumerConfig.GROUP_ID_CONFIG, GROUP_ID);
////        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
////        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
////        return new DefaultKafkaConsumerFactory<>(props);
////    }
//
//    // 2. Consume user objects from Kafka
//    public ConsumerFactory<String, Message> userConsumerFactory() {
//        Map<String, Object> props = new HashMap<>();
//        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//        props.put(ConsumerConfig.GROUP_ID_CONFIG, MESSAGE_ID);
//        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
//        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,KafkaMessageDeserializer.class);
//        return new DefaultKafkaConsumerFactory<>(props);
//    }
//
//
//    @Bean
//    public ConcurrentKafkaListenerContainerFactory<String, Message> userKafkaListenerContainerFactory() {
//        ConcurrentKafkaListenerContainerFactory<String, Message> factory = new ConcurrentKafkaListenerContainerFactory<>();
//        factory.setConsumerFactory(userConsumerFactory());
//        factory.setMessageConverter(new StringJsonMessageConverter());
//        return factory;
//    }
//}
