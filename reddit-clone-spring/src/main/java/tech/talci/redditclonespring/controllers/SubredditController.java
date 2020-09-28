package tech.talci.redditclonespring.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.dto.SubredditDto;
import tech.talci.redditclonespring.services.SubredditService;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
@AllArgsConstructor
@Slf4j
public class SubredditController {

    private SubredditService subredditService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubredditDto createSubreddit(@RequestBody SubredditDto subredditDto) {
        return subredditService.save(subredditDto);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<SubredditDto> getAllSubreddits() {
        return subredditService.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public SubredditDto getSubreddit(@PathVariable Long id) {
        return subredditService.findById(id);
    }
}
