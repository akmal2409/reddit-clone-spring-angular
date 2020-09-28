package tech.talci.redditclonespring.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.dto.CommentDto;
import tech.talci.redditclonespring.services.CommentService;

@RestController
@RequestMapping("/api/cpmments")
@Slf4j
@AllArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.save(commentDto);
    }

    @GetMapping("/{postId}")
    @ResponseStatus(HttpStatus.OK)
    public void getAllCommentsForPost(@PathVariable Long postId){
        commentService.getAllCommentsForPost(postId);
    }

    @GetMapping("/by-user/{username}")
    @ResponseStatus(HttpStatus.OK)
    public void getAllUsersComments(@PathVariable String username) {
        commentService.getAllUsersComments(username);
    }
}
