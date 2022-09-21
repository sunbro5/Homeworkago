package com.example.external.rides.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

public record Ride(String id, @JsonProperty("created_at") Instant createdAt, String pickup, String destination,
                   @JsonProperty("passenger_id") int passengerId, @JsonProperty("driver_id") int driverId) {
}
