package com.klaudiusz.demonstration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    // DTO for Comment model.
    private Long id;
    private String postId;
    private String name;
    private String email;
    private String body;
}