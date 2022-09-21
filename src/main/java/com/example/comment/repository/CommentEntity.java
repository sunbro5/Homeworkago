package com.example.comment.repository;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public record CommentEntity(@Indexed String rideId, String text) {
}
