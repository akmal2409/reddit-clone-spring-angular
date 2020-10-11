package tech.talci.redditclonespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.redditclonespring.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByToken(String token);
}
