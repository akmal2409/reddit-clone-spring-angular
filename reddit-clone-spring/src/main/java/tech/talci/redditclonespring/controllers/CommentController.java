package tech.talci.redditclonespring.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.dto.CommentDto;
import tech.talci.redditclonespring.services.CommentService;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@Slf4j
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
    }

    @GetMapping("/by-post/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllCommentsForPost(@PathVariable Long postId){
        return commentService.getAllCommentsForPost(postId);
    }

    @GetMapping("/by-user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllUsersComments(@PathVariable String username) {
        return commentService.getAllUsersComments(username);
    }
}
