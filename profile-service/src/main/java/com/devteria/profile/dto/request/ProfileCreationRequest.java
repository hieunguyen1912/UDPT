package com.devteria.profile.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class ProfileCreationRequest {
    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String city;
}
