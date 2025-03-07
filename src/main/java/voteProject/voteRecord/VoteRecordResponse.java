package voteProject.voteRecord;

public record VoteRecordResponse(Long userId,
                                 Long voteId,
                                 Long voteOptionId) {
}