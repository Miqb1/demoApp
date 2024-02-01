package com.klaudiusz.demonstration.controller;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    CommentService commentService;

    // Request to retrieve data using the GET function for all positions.
    @GetMapping("comment")
    public ResponseEntity<List<CommentDto>> getAllComments() throws CustomHttpException {
        final List<CommentDto> list = commentService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    // Request to retrieve data using the GET function for a specific position.
    @GetMapping(path = "comment/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable final Long id) throws CustomHttpException {
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
    public ResponseEntity<String> updateComment(@PathVariable final Long id, @RequestBody final CommentDto commentDto) throws CustomHttpException {

        final boolean update = commentService.updateComment(id, commentDto);
        if (update) {
            return ResponseEntity.ok("Comment updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Comment with ID " + id + " not found");
        }
    }

    // Request to delete a specific entry using the DELETE function.
    @DeleteMapping("comment/{id}")
    public ResponseEntity<String> deleteById(@PathVariable final Long id) throws CustomHttpException {
        commentService.deleteById(id);
        return ResponseEntity.ok("Comment deleted Successfully");
    }
}