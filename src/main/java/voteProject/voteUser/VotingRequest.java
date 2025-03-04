package voteProject.voteUser;

public record VotingRequest(Long voteUserId,
                            Long voteId,
                            Long voteOptionId) {
}
