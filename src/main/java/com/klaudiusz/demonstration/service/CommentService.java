package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.mapper.CommentMapper;
import com.klaudiusz.demonstration.model.Comment;
import com.klaudiusz.demonstration.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    CommentRepository commentRepository;

    CommentService(final CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    //  Retrieves a list of all users from the database.
    public List<CommentDto> list() {
        final List<Comment> comments = commentRepository.getAllPositions();
        return CommentMapper.MAPPER.mapListToCommentDtoList(comments);
    }

    //  Retrieves a user with a specified identifier.
    public CommentDto getCommentById(final Long id) {
        final Comment comment = commentRepository.getOnePosition(id);
        if (comment != null) {
            return CommentMapper.MAPPER.mapToCommentDto(comment);
        }
        return null;
    }

    //  Saves a new user to the database.
    public CommentDto create(final CommentDto commentDto) throws CustomHttpException {
        final Comment comment = commentRepository.saveComments(CommentMapper.MAPPER.mapToComment(commentDto));
        return CommentMapper.MAPPER.mapToCommentDto(comment);
    }

    //  Deletes a specific position.
    public void deleteById(final Long id) throws CustomHttpException {
        commentRepository.deleteCommentById(id);
    }

    //  Deletes all positions.
    public void deleteAll() throws CustomHttpException {
        commentRepository.deleteComments();
    }
}