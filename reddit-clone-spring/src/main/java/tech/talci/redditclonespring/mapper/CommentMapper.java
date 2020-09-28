package tech.talci.redditclonespring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import tech.talci.redditclonespring.domain.Comment;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.dto.CommentDto;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDto.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment commentDtoToComment(CommentDto commentDto, Post post, User user);

    @Mapping(target = "postId", expression = "java(comment.getPost().getPostId())")
    @Mapping(target = "username", expression = "java(comment.getUser().getUsername())")
    CommentDto commentToCommentDto(Comment comment);
}
