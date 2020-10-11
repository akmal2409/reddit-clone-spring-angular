package tech.talci.redditclonespring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;
import tech.talci.redditclonespring.config.SwaggerConfig;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfig.class)
public class RedditCloneSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneSpringApplication.class, args);
    }

}
