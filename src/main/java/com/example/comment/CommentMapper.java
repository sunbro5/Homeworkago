package com.example.comment;

import com.example.comment.model.CommentAddRequest;
import com.example.comment.repository.CommentEntity;
import com.example.comment.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CommentMapper {

    Comment toComment(CommentEntity comment);

    CommentEntity toCommentEntity(CommentAddRequest commentAddRequest);

}
