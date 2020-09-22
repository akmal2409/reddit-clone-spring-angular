package tech.talci.redditclonespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.domain.Vote;

import java.util.Optional;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    Optional<Vote> findTopByPostAndUserOrderByVoteIdDesc(Post post, User user);
}
