package com.devteria.chat.service;

import com.devteria.chat.configuration.KafkaConfig;
import com.devteria.chat.event.ChatMessageEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageProducer {
    KafkaTemplate<String, ChatMessageEvent> kafkaTemplate;

    public void sendMessage(ChatMessageEvent event) {
        log.info("Publishing message to Kafka: conversationId={}", event.getConversationId());

        kafkaTemplate.send(KafkaConfig.CHAT_MESSAGE_TOPIC,
                        event.getConversationId(),
                        event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        log.info("Message sent successfully: {}", event.getMessageId());
                    } else {
                        log.error("Failed to send message", ex);
                    }
                });
    }
}
