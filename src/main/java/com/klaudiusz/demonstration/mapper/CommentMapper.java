package com.klaudiusz.demonstration.mapper;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CommentMapper {
    CommentMapper MAPPER = Mappers.getMapper(CommentMapper.class);

    Comment mapToComment(CommentDto commentDto);

    CommentDto mapToCommentDto(Comment comment);

    List<CommentDto> mapListToCommentDtoList(List<Comment> comments);
}