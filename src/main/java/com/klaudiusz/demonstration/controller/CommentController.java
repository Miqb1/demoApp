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

    //  Constructor of CommentController class.
    CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    // Request to retrieve data using the GET function for all positions.
    @GetMapping("comment")
    public ResponseEntity<List<CommentDto>> getAllComments() {
        final List<CommentDto> list = commentService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Request to retrieve data using the GET function for a specific position.
    @GetMapping(path = "comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable final Long id) {
        final CommentDto oneComment = commentService.getCommentById(id);
        return new ResponseEntity<>(oneComment, HttpStatus.OK);
    }

    // Request to create a new entry using the POST function.
    @PostMapping("addComment")
    public ResponseEntity<CommentDto> createComment(@RequestBody final CommentDto commentDto) throws CustomHttpException {
        final CommentDto newComment = commentService.create(commentDto);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    // Request to partially update data for a specific position using the PUT function.
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

    // Request to delete a specific entry using the DELETE function.
    @DeleteMapping("comment/{id}")
    public ResponseEntity<String> deleteById(@PathVariable final Long id) throws CustomHttpException {
        commentService.deleteById(id);
        return ResponseEntity.ok("Comment deleted Successfully");
    }

    // Request to delete all entry's using the DELETE function.
    @DeleteMapping("comment")
    public ResponseEntity<String> deleteComments() {
        try {
            commentService.deleteAll();
            return ResponseEntity.ok("All comments deleted");
        } catch (final CustomHttpException e) {
            return ResponseEntity.status(e.getStatus()).body(e.getMessage());
        }
    }
}