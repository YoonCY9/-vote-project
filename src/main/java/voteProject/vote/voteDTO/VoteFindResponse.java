package voteProject.vote.voteDTO;

import java.time.LocalDateTime;

public record VoteFindResponse(
        Long voteId,
        String title,
        Long totalVote,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
