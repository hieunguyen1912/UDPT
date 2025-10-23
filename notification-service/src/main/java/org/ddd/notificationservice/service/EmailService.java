package org.ddd.notificationservice.service;

import feign.FeignException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.ddd.notificationservice.dto.request.EmailRequest;
import org.ddd.notificationservice.dto.request.SendEmailRequest;
import org.ddd.notificationservice.dto.response.EmailResponse;
import org.ddd.notificationservice.exception.AppException;
import org.ddd.notificationservice.exception.ErrorCode;
import org.ddd.notificationservice.repository.httpclient.EmailClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailService {

    EmailClient emailClient;

    String api_key = "re_U7V5ucXw_7GW5r9hb39y8pJKVs1VY9H1H";

    public EmailResponse sendEmail(SendEmailRequest request) {
        EmailRequest emailRequest = EmailRequest.builder()
                .from("Acme <onboarding@resend.dev>")
                .to(List.of(request.getTo()))
                .subject(request.getSubject())
                .html(request.getHtml())
                .build();
        try {
            return emailClient.sendEmail("Bearer " + api_key, emailRequest);
        } catch (FeignException e) {
            log.info("Exception: ", e);
            throw new AppException(ErrorCode.CANNOT_SEND_EMAIL);
        }
    }
}
