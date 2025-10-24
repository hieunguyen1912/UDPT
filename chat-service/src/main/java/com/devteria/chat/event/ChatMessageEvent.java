package com.devteria.chat.event;

import com.devteria.chat.entity.ChatMessage;
import com.devteria.chat.entity.ParticipantInfo;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessageEvent {
    String conversationId;
    String message;
    ParticipantInfo sender;
    Instant createdDate;
    String messageId;
    List<String> participantIds; // Thêm list participants để consumer biết gửi tới ai

    public static ChatMessageEvent fromChatMessage(ChatMessage chatMessage,
                                                   List<String> participantIds) {
        return ChatMessageEvent.builder()
                .conversationId(chatMessage.getConversationId())
                .message(chatMessage.getMessage())
                .sender(chatMessage.getSender())
                .createdDate(chatMessage.getCreatedDate())
                .messageId(chatMessage.getId())
                .participantIds(participantIds)
                .build();
    }
}