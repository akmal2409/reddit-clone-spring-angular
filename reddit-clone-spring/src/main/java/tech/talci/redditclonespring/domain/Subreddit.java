package tech.talci.redditclonespring.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Subreddit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Subreddit name cannot be blank!")
    private String name;

    @NotBlank(message = "Description cannot be blank!")
    private String description;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Post> posts;
    private Instant createdDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
