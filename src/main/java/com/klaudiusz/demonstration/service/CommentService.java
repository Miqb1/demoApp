package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.mapper.CommentMapper;
import com.klaudiusz.demonstration.model.Comment;
import com.klaudiusz.demonstration.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@SuppressWarnings("unused")
public class CommentService {

    private CommentRepository commentRepository;

    //  Retrieves a list of all comments from the database.
    public List<CommentDto> list() {
        final List<Comment> comments = commentRepository.getAllPositions();
        return CommentMapper.MAPPER.mapListToCommentDtoList(comments);
    }

    //  Retrieves a comment with a specified identifier.
    public CommentDto getCommentById(final Long id) throws CustomHttpException {
        final Comment comment = commentRepository.getOnePosition(id);
        if (comment != null) {
            return CommentMapper.MAPPER.mapToCommentDto(comment);
        }
        return null;
    }

    //  Saves a new comment to the database.
    public CommentDto create(final CommentDto commentDto) throws CustomHttpException {
        final Comment comment = commentRepository.saveComments(CommentMapper.MAPPER.mapToComment(commentDto));
        return CommentMapper.MAPPER.mapToCommentDto(comment);
    }

    // Updates eMail and Body of a specific position defined by its ID/
    public boolean updateComment(final Long id, final CommentDto commentDto) throws CustomHttpException {
        if (id != null) {
            final Comment comment = commentRepository.updateComment(id, CommentMapper.MAPPER.mapToComment(commentDto));
            return true;
        } else
            return false;
    }

    //  Deletes a specific position.
    public void deleteById(final Long id) throws CustomHttpException {
        commentRepository.deleteCommentById(id);
    }
}