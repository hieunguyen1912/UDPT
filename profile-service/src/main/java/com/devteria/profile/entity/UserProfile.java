package com.devteria.profile.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.data.annotation.Id;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;

@Node("user_profile")
@Getter
@Setter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProfile {
    @Id
    @GeneratedValue(generatorClass = UUIDStringGenerator.class)
    String id;

    @Property("userId")
    String userId;

    String firstName;
    String lastName;
    LocalDate dob;
    String city;
}
