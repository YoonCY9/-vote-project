package voteProject.voteUser;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteUserRepository extends JpaRepository<VoteUser, Long> {
    boolean existsByNickname(String nickname);
}
