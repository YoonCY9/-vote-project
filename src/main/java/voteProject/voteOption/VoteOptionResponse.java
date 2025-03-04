package voteProject.voteOption;

public record VoteOptionResponse(Long voteOptionId,
                                 String content,
                                 int count) {
}
