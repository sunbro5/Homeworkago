package com.example.comment;

import com.example.comment.model.Comment;
import com.example.comment.model.CommentAddRequest;
import com.example.comment.repository.CommentEntity;
import com.example.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public List<Comment> getCommentForRideId(String rideId){
        return commentRepository.findCommentByRideId(rideId).stream()
                .map(commentMapper::toComment)
                .toList();
    }

    public void addComment(CommentAddRequest commentAddRequest) {
        CommentEntity comment = commentMapper.toCommentEntity(commentAddRequest);
        commentRepository.save(comment);
    }
}
