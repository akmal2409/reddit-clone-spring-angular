package tech.talci.redditclonespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.redditclonespring.domain.Comment;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.User;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost(Post post);

    List<Comment> findAllByUser(User user);
}
