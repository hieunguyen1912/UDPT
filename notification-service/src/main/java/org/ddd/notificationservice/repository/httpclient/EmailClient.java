package org.ddd.notificationservice.repository.httpclient;

import org.ddd.notificationservice.dto.request.EmailRequest;
import org.ddd.notificationservice.dto.response.EmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "email-client", url = "https://api.resend.com")
public interface EmailClient {
    @PostMapping(value = "/emails", produces = MediaType.APPLICATION_JSON_VALUE)
    EmailResponse sendEmail(@RequestHeader("Authorization") String apiKey, @RequestBody EmailRequest body);
}
