package com.billo.notification.config;

import com.billo.clients.models.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;
import java.util.Map;

public class KafkaMessageDeserializer
        implements Deserializer<Message> {

    @Override
    public void configure(Map<String, ?> map, boolean b) {}

    @Override
    public Message deserialize(String s, byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new String(bytes,
                    StandardCharsets.UTF_8), Message.class);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to messageDto");
        }
    }

    @Override
    public void close() {}
}