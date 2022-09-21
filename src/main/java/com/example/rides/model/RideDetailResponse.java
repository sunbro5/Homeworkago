package com.example.rides.model;

import com.example.comment.model.Comment;

import java.time.Instant;
import java.util.List;

public record RideDetailResponse(Instant createdAt, String driverName, String pickup, String destination, int driverId,
                                 String passengerName, List<Comment> comments) {
}
