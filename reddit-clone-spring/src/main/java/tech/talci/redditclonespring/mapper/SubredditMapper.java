package tech.talci.redditclonespring.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tech.talci.redditclonespring.domain.Post;
import tech.talci.redditclonespring.domain.Subreddit;
import tech.talci.redditclonespring.dto.SubredditDto;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubredditMapper {

    @Mapping(target = "numberOfPosts", expression = "java(mapPosts(subreddit.getPosts()))")
    SubredditDto subredditToSubredditDto(Subreddit subreddit);

    @InheritInverseConfiguration
    @Mapping(target = "posts", ignore = true)
    Subreddit subredditDtoToSubreddit(SubredditDto subredditDto);

    default Integer mapPosts(List<Post> numberOfPosts) {
        return numberOfPosts.size();
    }
}
