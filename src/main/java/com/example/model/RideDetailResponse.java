package com.example.model;

public record RideDetailResponse(String timestamp, String driverName, String pickup, String destination,
                                 int driverId, String passengerName, String comment) {
}
