package voteProject.vote.voteDTO;

import voteProject.vote.VoteType;

import java.time.LocalDateTime;
import java.util.List;

public record VoteResponse(
        Long voteId,
        String title,
        List<VoteOptionResponse> voteContent,
        VoteType voteType,
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
