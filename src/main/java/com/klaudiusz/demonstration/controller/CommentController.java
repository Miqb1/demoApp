package com.klaudiusz.demonstration.controller;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

public class CommentController {

    CommentService commentService;

    CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("comment")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        final List<CommentDto> list = commentService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable final Long id) {
        final CommentDto oneComment = commentService.getCommentById(id);
        return new ResponseEntity<>(oneComment, HttpStatus.OK);
    }

    @PostMapping("addComment")
    public ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto commentDto) throws CustomHttpException {
        final CommentDto newComment = commentService.create(commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping(path = "comment/{id}")
    public ResponseEntity<CommentDto> changeComment(@PathVariable final Long id, @RequestBody final CommentDto commentDto) {
        try {
            CommentDto previousComment = commentService.getCommentById(id);
            if (commentDto.getBody() != null) {
                previousComment.setBody(commentDto.getBody());
            }
            if (commentDto.getEmail() != null) {
                previousComment.setEmail(commentDto.getEmail());
            }
            previousComment = commentService.create(previousComment);
            return new ResponseEntity<>(previousComment, HttpStatus.OK);
        } catch (final Exception e) {
            return new ResponseEntity<>(new CommentDto(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("comment/{id}")
    public ResponseEntity<String> deleteById(@PathVariable final Long id) throws CustomHttpException {
        commentService.deleteById(id);
        return ResponseEntity.ok("Comment deleted Successfully");
    }

    @DeleteMapping("comment")
    public ResponseEntity<String> deleteComments(){
        try {
            commentService.deleteAll();
            return ResponseEntity.ok("All comments deleted");
        } catch (final CustomHttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}
