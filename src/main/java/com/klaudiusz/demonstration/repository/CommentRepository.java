package com.klaudiusz.demonstration.repository;

import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.model.Comment;

import java.util.List;

public interface CommentRepository {
    List<Comment> getAllPositions();

    Comment getOnePosition(final Long id) throws CustomHttpException;

    Comment saveComments(final Comment comment) throws CustomHttpException;

    void deleteCommentById(final Long id) throws CustomHttpException;
}
