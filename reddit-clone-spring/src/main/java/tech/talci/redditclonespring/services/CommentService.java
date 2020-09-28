package tech.talci.redditclonespring.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.talci.redditclonespring.domain.Comment;
import tech.talci.redditclonespring.domain.NotificationEmail;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.dto.CommentDto;
import tech.talci.redditclonespring.exceptions.RedditException;
import tech.talci.redditclonespring.exceptions.ResourceNotFoundException;
import tech.talci.redditclonespring.mapper.CommentMapper;
import tech.talci.redditclonespring.repositories.CommentRepository;
import tech.talci.redditclonespring.repositories.PostRepository;
import tech.talci.redditclonespring.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final AuthService authService;
    private final CommentMapper commentMapper;
    private final MailService mailService;
    private final MailContentBuilder contentBuilder;


    public void save(CommentDto commentDto) {
        Post fetchedPost = postRepository
                .findById(commentDto.getPostId())
                .orElseThrow(() -> new ResourceNotFoundException("Post was not found!"));
        Comment newComment = commentMapper.commentDtoToComment(commentDto, fetchedPost, authService.getCurrentUser());

        commentRepository.save(newComment);
        String message = contentBuilder.build(fetchedPost.getUser().getUsername() + " commented on your post. <URL>");
        sendCommentNotification(message, fetchedPost.getUser());
    }

    private void sendCommentNotification(String message, User user) {
        mailService.sendMail(new NotificationEmail(user.getUsername() + " Commented on your post",
                user.getEmail(), message));
    }

    public List<CommentDto> getAllCommentsForPost(Long postId) {
        Post fetchedPost = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post was not found"));

        return commentRepository
                .findAllByPost(fetchedPost)
                .stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }

    public List<CommentDto> getAllUsersComments(String username) {
        User fetchedUser = userRepository
                .findUserByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User was not found"));

        return commentRepository
                .findAllByUser(fetchedUser)
                .stream()
                .map(commentMapper::commentToCommentDto)
                .collect(Collectors.toList());
    }
}
