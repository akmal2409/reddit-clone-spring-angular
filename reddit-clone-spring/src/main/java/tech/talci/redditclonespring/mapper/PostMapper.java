package tech.talci.redditclonespring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.domain.User;
import tech.talci.redditclonespring.dto.PostRequest;
import tech.talci.redditclonespring.dto.PostResponse;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "postRequest.description")
    Post map(PostRequest postRequest, Subreddit subreddit, User user);

    @Mapping(target = "id", source = "postId")
    @Mapping(target = "subredditName", source = "subreddit.name")
    @Mapping(target = "username", source = "user.username")
    PostResponse mapToPostResponse(Post post);

}
