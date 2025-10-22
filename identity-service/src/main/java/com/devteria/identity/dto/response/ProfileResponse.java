package com.devteria.identity.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProfileResponse {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String city;
}
