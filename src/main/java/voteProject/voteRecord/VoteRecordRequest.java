package voteProject.voteRecord;

public record VoteRecordRequest(Long userId,
                                Long voteId,
                                Long voteOption) {
}
