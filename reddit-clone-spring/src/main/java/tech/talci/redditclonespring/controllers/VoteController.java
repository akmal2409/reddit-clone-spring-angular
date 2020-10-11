package tech.talci.redditclonespring.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tech.talci.redditclonespring.dto.VoteDto;
import tech.talci.redditclonespring.services.VoteService;

@RestController
@RequestMapping("/api/votes/")
@AllArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void vote(@RequestBody VoteDto voteDto) {
        voteService.vote(voteDto);
    }


}
