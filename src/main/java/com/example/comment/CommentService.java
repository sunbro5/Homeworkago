package com.example.comment;

import com.example.comment.model.Comment;
import com.example.comment.model.CommentAddRequest;
import com.example.comment.repository.CommentEntity;
import com.example.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    @Async
    public CompletableFuture<List<Comment>> getCommentForRideId(String rideId){
        List<Comment> comments = commentRepository.findCommentByRideId(rideId).stream()
                .map(commentMapper::toComment)
                .toList();

        return CompletableFuture.completedFuture(comments);
    }

    public void addComment(CommentAddRequest commentAddRequest) {
        CommentEntity comment = commentMapper.toCommentEntity(commentAddRequest);
        commentRepository.save(comment);
    }
}
