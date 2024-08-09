package com.example.Newsline.web.controllers;


import com.example.Newsline.aop.Loggable;
import com.example.Newsline.mapper.CommentMapper;
import com.example.Newsline.model.Comment;
import com.example.Newsline.service.CommentService;
import com.example.Newsline.web.model.CommentListResponse;
import com.example.Newsline.web.model.CommentResponse;
import com.example.Newsline.web.model.UpsertCommentRequest;
import com.example.Newsline.web.model.filters.CommentFilter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService databaseCommentService;

    private final CommentMapper commentMapper;

    @GetMapping("/filter")
    public ResponseEntity<CommentListResponse> filterAllByNewsId(@PathVariable Long newsId) {
        CommentFilter filter = new CommentFilter();
        filter.setNewsId(newsId);

        return ResponseEntity.ok(
                commentMapper.commentListToCommentListResponse(
                        databaseCommentService.findAllByNewsId(filter)
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(commentMapper.commentToResponse(
                databaseCommentService.findById(id)
        ));
    }

    @PostMapping
    public ResponseEntity<CommentResponse> create(@RequestBody @Valid UpsertCommentRequest request) {
        Comment newComment = databaseCommentService.save(commentMapper.requestToComment(request));

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(commentMapper.commentToResponse(newComment));
    }

    @PostMapping("/{commentId}")
    @Loggable
    public ResponseEntity<CommentResponse> update(@PathVariable Long commentId, @RequestParam Long userId, @RequestBody @Valid UpsertCommentRequest request) {
        Comment updatedComment = databaseCommentService.update(commentMapper.requestToComment(commentId, request));

        return ResponseEntity.ok(commentMapper.commentToResponse(updatedComment));
    }


    @DeleteMapping("/{commentId}")
    @Loggable
    public ResponseEntity<Void> delete(@PathVariable Long commentId, @RequestParam Long userId) {
        databaseCommentService.deleteById(commentId);

        return ResponseEntity.noContent().build();
    }

}
