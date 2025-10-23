package org.ddd.notificationservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class EmailRequest {
    private String from;
    private List<String> to;
    private String subject;
    private String html;
    private String replyTo;
}
