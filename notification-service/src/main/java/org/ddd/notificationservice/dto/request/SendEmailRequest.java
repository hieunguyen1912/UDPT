package org.ddd.notificationservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;



@Data
@Builder
@AllArgsConstructor
public class SendEmailRequest {
    private String to;
    private String subject;
    private String html;
}
