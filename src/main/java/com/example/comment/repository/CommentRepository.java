package com.example.comment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CommentRepository extends MongoRepository<CommentEntity, String> {

    @Query("{ 'rideId' : ?0 }")
    List<CommentEntity> findCommentByRideId(String rideId);
}
