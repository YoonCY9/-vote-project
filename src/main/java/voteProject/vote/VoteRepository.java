package voteProject.vote;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    List<Vote> findByTitleContaining(String title);

    List<Vote> findByCreateAtAfter(LocalDateTime localDateTime);

    List<Vote> findByEndDate(LocalDateTime localDateTime);
}
