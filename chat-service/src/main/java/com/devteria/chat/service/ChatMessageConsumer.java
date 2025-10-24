package com.devteria.chat.service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.devteria.chat.configuration.KafkaConfig;
import com.devteria.chat.dto.response.ChatMessageResponse;
import com.devteria.chat.entity.WebSocketSession;
import com.devteria.chat.event.ChatMessageEvent;
import com.devteria.chat.repository.WebSocketSessionRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatMessageConsumer {
    SocketIOServer socketIOServer;
    WebSocketSessionRepository webSocketSessionRepository;
    ObjectMapper objectMapper;

    @KafkaListener(topics = KafkaConfig.CHAT_MESSAGE_TOPIC,
            groupId = KafkaConfig.REALTIME_CONSUMER_GROUP,
            containerFactory = "kafkaListenerContainerFactory")
    public void consumeAndPushRealtime(ChatMessageEvent event,
                                       Acknowledgment acknowledgment) {
        try {
            log.info("Real-time Consumer: Processing message={}, conversationId={}",
                    event.getMessageId(), event.getConversationId());

            // Get all WebSocket sessions of participants
            List<WebSocketSession> webSocketSessions =
                    webSocketSessionRepository.findAllByUserIdIn(event.getParticipantIds());

            Map<String, WebSocketSession> sessionMap = webSocketSessions.stream()
                    .collect(Collectors.toMap(
                            WebSocketSession::getSocketSessionId,
                            Function.identity()));

            // Get all connected clients
            Collection<SocketIOClient> allClients = socketIOServer.getAllClients();

            if (allClients.isEmpty()) {
                log.debug("No online clients for this conversation");
                acknowledgment.acknowledge();
                return;
            }

            // Push to online clients
            int pushCount = 0;
            for (SocketIOClient client : allClients) {
                String socketSessionId = client.getSessionId().toString();
                WebSocketSession wsSession = sessionMap.get(socketSessionId);

                if (wsSession != null) {
                    try {
                        String senderUserId = event.getSender().getUserId();
                        boolean isMe = senderUserId.equals(wsSession.getUserId());

                        // Build response
                        ChatMessageResponse response = ChatMessageResponse.builder()
                                .id(event.getMessageId())
                                .conversationId(event.getConversationId())
                                .message(event.getMessage())
                                .sender(event.getSender())
                                .createdDate(event.getCreatedDate())
                                .me(isMe)
                                .build();

                        String messageJson = objectMapper.writeValueAsString(response);
                        client.sendEvent("message", messageJson);
                        pushCount++;

                    } catch (JsonProcessingException e) {
                        log.error("Error serializing message for client: {}",
                                socketSessionId, e);
                    }
                }
            }

            log.info("Real-time Consumer: Pushed message to {} clients", pushCount);

            // Acknowledge after successful push
            acknowledgment.acknowledge();

        } catch (Exception e) {
            log.error("Error in Real-time Consumer", e);
            // Don't acknowledge - Kafka will retry
        }
    }
}