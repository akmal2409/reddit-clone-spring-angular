package tech.talci.redditclonespring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.dto.PostRequest;
import tech.talci.redditclonespring.dto.PostResponse;
import tech.talci.redditclonespring.services.PostService;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPost(@RequestBody PostRequest postRequest){
        postService.save(postRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostResponse getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("by-subreddit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsBySubreddit(@PathVariable Long id){
        return postService.getPostsBySubreddit(id);
    }

    @GetMapping("by-user/{name}")
    @ResponseStatus(HttpStatus.OK)
    public List<PostResponse> getPostsByUsername(@PathVariable String name){
        return postService.getPostsByUsername(name);
    }
}
