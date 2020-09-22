package tech.talci.redditclonespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.domain.User;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    List<Post> findAllBySubreddit(Subreddit subreddit);

    List<Post> findByUser(User user);
}
