package com.example.external.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record User(int id, @JsonProperty("first_name") String firstName, @JsonProperty("last_name") String lastName,
                   String email, @JsonProperty("phone_number") String phoneNumber, List<UserRole> roles) {
}
