package tech.talci.redditclonespring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.talci.redditclonespring.domain.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    Optional<VerificationToken> findByToken(String token);
}
