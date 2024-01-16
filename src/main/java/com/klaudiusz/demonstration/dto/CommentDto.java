package com.klaudiusz.demonstration.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    @Id
    private String postId;
    private Long id;
    private String name;
    private String email;
    private String body;
}
