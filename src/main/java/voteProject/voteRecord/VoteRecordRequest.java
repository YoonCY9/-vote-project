package voteProject.voteRecord;

public record VoteRecordRequest(
        Long voteId,
        Long voteUserId,
        Long voteOptionId,
        boolean isAnonymous
) {
}