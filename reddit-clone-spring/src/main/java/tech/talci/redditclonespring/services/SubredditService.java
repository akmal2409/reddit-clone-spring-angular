package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.dto.SubredditDto;
import tech.talci.redditclonespring.repositories.SubredditRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit saved = subredditRepository.save(mapSubredditDto(subredditDto));
        subredditDto.setId(saved.getId());

        return subredditDto;
    }

    private Subreddit mapSubredditDto(SubredditDto subredditDto) {
        return Subreddit.builder().name(subredditDto.getSubredditName())
                .description(subredditDto.getDescription())
                .createdDate(Instant.now())
                .build();
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {

        return subredditRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private SubredditDto mapToDto(Subreddit subreddit) {

        SubredditDto subredditDto = new SubredditDto();
        subredditDto.setDescription(subreddit.getDescription());
        subredditDto.setId(subreddit.getId());
        subredditDto.setSubredditName(subreddit.getName());
        subredditDto.setNumberOfPosts(subreddit.getPosts().size());

        return subredditDto;
    }
}
