package com.example.rides.model;

import com.example.comment.model.Comment;

import java.util.List;

public record RideDetailResponse(String createdAt, String driverName, String pickup, String destination, int driverId,
                                 String passengerName, List<Comment> comments) {
}
