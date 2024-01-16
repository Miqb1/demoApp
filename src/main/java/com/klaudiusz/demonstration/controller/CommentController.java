package com.klaudiusz.demonstration.controller;

import com.klaudiusz.demonstration.dto.CommentDto;
import com.klaudiusz.demonstration.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    CommentService commentService;

    CommentController(final CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("comment")
    public ResponseEntity<List<CommentDto>> getAllComments(){
        List<CommentDto> list = commentService.list();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
