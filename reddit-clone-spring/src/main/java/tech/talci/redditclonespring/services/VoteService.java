package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.Vote;
import tech.talci.redditclonespring.dto.VoteDto;
import tech.talci.redditclonespring.exceptions.RedditException;
import tech.talci.redditclonespring.exceptions.ResourceNotFoundException;
import tech.talci.redditclonespring.repositories.PostRepository;
import tech.talci.redditclonespring.repositories.VoteRepository;

import java.util.Optional;

import static tech.talci.redditclonespring.domain.VoteType.*;

@Service
@Slf4j
@AllArgsConstructor
public class VoteService {

    private final VoteRepository voteRepository;
    private final PostRepository postRepository;
    private final AuthService authService;

    @Transactional
    public void vote(VoteDto voteDto) {
        Post fetchedPost = postRepository.findById(voteDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post was not found! ID: "
                        + voteDto.getPostId()));

        Optional<Vote> voteByPostAndUser = voteRepository
                .findTopByPostAndUserOrderByVoteIdDesc(fetchedPost, authService.getCurrentUser());

        if(voteByPostAndUser.isPresent() &&
                voteByPostAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new RedditException("You have already voted " + voteDto.getVoteType());
        }

        if(UPVOTE.equals(voteDto.getVoteType())) {
            fetchedPost.setVoteCount(fetchedPost.getVoteCount() + 1);
        } else {
            fetchedPost.setVoteCount(fetchedPost.getVoteCount() - 1);
        }

        voteRepository.save(mapToDto(voteDto, fetchedPost));
        postRepository.save(fetchedPost);
    }

    private Vote mapToDto(VoteDto voteDto, Post post) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .post(post)
                .user(authService.getCurrentUser())
                .build();
    }
}
