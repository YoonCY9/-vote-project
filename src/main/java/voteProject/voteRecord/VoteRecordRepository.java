package voteProject.voteRecord;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VoteRecordRepository extends JpaRepository<VoteRecord, Long> {
    Optional<VoteRecord> findByVoteUserIdAndVoteIdAndVoteOptionId(Long voteUserId, Long voteId, Long voteOptionId);
}
