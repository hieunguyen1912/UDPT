package org.ddd.notificationservice.controller;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.ddd.notificationservice.dto.request.SendEmailRequest;
import org.ddd.notificationservice.dto.response.ApiResponse;
import org.ddd.notificationservice.dto.response.EmailResponse;
import org.ddd.notificationservice.service.EmailService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    EmailService emailService;

    @PostMapping("/email/send")
    public ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest emailRequest) {
        return ApiResponse.<EmailResponse>builder()
                .result(emailService.sendEmail(emailRequest)).build();
    }
}
