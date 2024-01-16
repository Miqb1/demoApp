package com.klaudiusz.demonstration.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaudiusz.demonstration.exceptions.CustomInterruptException;
import com.klaudiusz.demonstration.model.Comment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Repository
@Component
public class CommentRepository {

    private static final Logger CommentLOGGER = LoggerFactory.getLogger(CommentRepository.class);
    ObjectMapper objectMapper;

    HttpClient httpClient;
    @Value("${spring.comment.api.url}")
    private String URL;

    CommentRepository(final ObjectMapper objectMapper, final HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    public List<Comment> getAllPositions() {
        final HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(URL))
                .GET()
                .build();
        CommentLOGGER.warn("Request: {} --- {} ---{}", request.method(), request.uri(), request.bodyPublisher());

        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            CommentLOGGER.warn("Response: {} --- {} --- {}", request.method(), response.uri(), response.body().length());

            final Comment[] comments = objectMapper.readValue(response.body(), Comment[].class);
            return Arrays.asList(comments);
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptException("Task interrupted while getting all positions", e);
        }

    }
}
