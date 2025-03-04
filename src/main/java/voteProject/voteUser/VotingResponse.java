package voteProject.voteUser;

public record VotingResponse(Long voteUserId,
                             Long voteId,
                             Long voteOptionId) {
}
