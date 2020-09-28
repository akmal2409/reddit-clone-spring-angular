package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.dto.SubredditDto;
import tech.talci.redditclonespring.exceptions.RedditException;
import tech.talci.redditclonespring.mapper.SubredditMapper;
import tech.talci.redditclonespring.repositories.SubredditRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;

    @Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit saved = subredditRepository.save(subredditMapper.subredditDtoToSubreddit(subredditDto));
        subredditDto.setId(saved.getId());

        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {

        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::subredditToSubredditDto)
                .collect(Collectors.toList());
    }


    public SubredditDto findById(Long id) {
        return subredditMapper.subredditToSubredditDto(subredditRepository.findById(id)
                .orElseThrow(() -> new RedditException("Subreddit was not found!")));
    }
}
