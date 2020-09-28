package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.dto.PostRequest;
import tech.talci.redditclonespring.dto.PostResponse;
import tech.talci.redditclonespring.exceptions.RedditException;
import tech.talci.redditclonespring.mapper.PostMapper;
import tech.talci.redditclonespring.repositories.PostRepository;
import tech.talci.redditclonespring.repositories.SubredditRepository;
import tech.talci.redditclonespring.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final SubredditRepository subredditRepository;
    private final PostMapper postMapper;
    private final AuthService authService;
    private final UserRepository userRepository;


    public void save(PostRequest postRequest) {

        Subreddit fetchedSubreddit = subredditRepository
                .findSubredditByName(postRequest.getSubredditName())
                .orElseThrow(() -> new RedditException("Subreddit was not found!"));

        User currentUser = authService.getCurrentUser();
        Post newPost = postMapper.map(postRequest, fetchedSubreddit, currentUser);
        newPost.setSubreddit(fetchedSubreddit);
        newPost.setUser(currentUser);
        Post savedPost =  postRepository.save(newPost);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long id) {
        return postMapper.mapToPostResponse(postRepository.findById(id)
        .orElseThrow(() -> new RedditException("Post was not found!")));
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postMapper::mapToPostResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsBySubreddit(Long subredditId) {
        Subreddit fetchedSubreddit = subredditRepository
                .findById(subredditId)
                .orElseThrow(() -> new RedditException("Subreddit was not found!"));

        return postRepository.findAllBySubreddit(fetchedSubreddit)
                .stream()
                .map(postMapper::mapToPostResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponse> getPostsByUsername(String username) {
        User user = userRepository.findUserByUsername(username)
                .orElseThrow(()-> new RedditException("Username was not found!"));

        return postRepository.findAllByUser(user)
                .stream()
                .map(postMapper::mapToPostResponse)
                .collect(Collectors.toList());
    }
}
