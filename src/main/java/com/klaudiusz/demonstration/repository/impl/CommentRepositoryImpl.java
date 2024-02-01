package com.klaudiusz.demonstration.repository.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.klaudiusz.demonstration.exceptions.CustomHttpException;
import com.klaudiusz.demonstration.exceptions.CustomInterruptException;
import com.klaudiusz.demonstration.model.Comment;
import com.klaudiusz.demonstration.repository.CommentRepository;
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

import static org.springframework.http.HttpStatus.valueOf;

@Repository
@Component
@SuppressWarnings("unused")
public class CommentRepositoryImpl implements CommentRepository {

    public static final String CONTENT_TYPE = "Content-Type";
    public static final String APPLICATION_JSON = "application/json";
    public static final String INTERRUPTED_WHILE_PERFORMING_OPERATION = "Task interrupted while performing operation";
    private static final Logger CommentLOGGER = LoggerFactory.getLogger(CommentRepositoryImpl.class);
    private final ObjectMapper objectMapper;
    private final HttpClient httpClient;

    @Value("${spring.comment.api.url}")
    private String url;

    public CommentRepositoryImpl(final ObjectMapper objectMapper, final HttpClient httpClient) {
        this.objectMapper = objectMapper;
        this.httpClient = httpClient;
    }

    //  Method for getting all positions.
    public List<Comment> getAllPositions() {
        final HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .GET()
                .build();
        CommentLOGGER.warn("Request: {} --- {}", request, request.bodyPublisher());
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            CommentLOGGER.warn("Response: {} --- {}", request, response.body().length());

            return Arrays.stream(objectMapper.readValue(response.body(), Comment[].class)).toList();
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptException("Task interrupted while getting all positions", e);
        }
    }

    //  Method for getting one position.
    public Comment getOnePosition(final Long id)  throws CustomHttpException {
        final HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + "/" + id))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .GET()
                .build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new CustomHttpException("Failed to get comment. Status code: ", valueOf(response.statusCode()));
            }

            return objectMapper.readValue(response.body(), Comment.class);
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptException(INTERRUPTED_WHILE_PERFORMING_OPERATION, e);
        }
    }

    //  Method for saving position.
    public Comment saveComments(final Comment comment) throws CustomHttpException {
        try {
            final HttpRequest request = HttpRequest
                    .newBuilder()
                    .uri(URI.create(url))
                    .header(CONTENT_TYPE, APPLICATION_JSON)
                    .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(comment)))
                    .build();
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 201) {
                throw new CustomHttpException("Failed to add comment. Status code: ", valueOf(response.statusCode()));
            }
            return comment;
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptException(INTERRUPTED_WHILE_PERFORMING_OPERATION, e);
        }
    }

    //  Method for deleting one position.
    public void deleteCommentById(final Long id) throws CustomHttpException {
        final HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url + "/" + id))
                .header(CONTENT_TYPE, APPLICATION_JSON)
                .DELETE()
                .build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new CustomHttpException("Failed to delete comment. Status code: ", valueOf(response.statusCode()));
            }
        } catch (final IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new CustomInterruptException(INTERRUPTED_WHILE_PERFORMING_OPERATION, e);
        }
    }
}