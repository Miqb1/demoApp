package com.klaudiusz.demonstration.service;

import com.klaudiusz.demonstration.dto.CarDto;
import com.klaudiusz.demonstration.dto.CommentDto;
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

    public List<CommentDto> list(){
        List<Comment> comments = commentRepository.getAllPositions();
        return CommentMapper.MAPPER.mapListToCommentDtoList(comments);

    }
}
