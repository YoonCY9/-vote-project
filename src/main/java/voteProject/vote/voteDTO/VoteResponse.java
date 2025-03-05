package voteProject.vote.voteDTO;

import java.time.LocalDateTime;
import java.util.List;

public record VoteResponse(
        Long voteId,
        String title,
        List<VoteOptionResponse> voteContent,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
