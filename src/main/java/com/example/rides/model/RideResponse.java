package com.example.rides.model;

import java.time.Instant;

public record RideResponse(Instant createdAt, String driverName, String pickup, String destination) {
}
