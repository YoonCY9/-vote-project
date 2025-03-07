package voteProject.vote.voteDTO;

import voteProject.vote.VoteType;
import java.time.LocalDateTime;
import java.util.List;

public record CreateVoteRequest(
        Long userId,
        String title,
        List<String> voteOptions,
        VoteType voteType,
        int durationDays
) {
}