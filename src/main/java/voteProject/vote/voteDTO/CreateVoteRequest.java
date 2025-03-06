package voteProject.vote.voteDTO;

import voteProject.vote.VoteType;
import voteProject.voteOption.VoteOptionRequest;
import java.time.LocalDateTime;
import java.util.List;

public record CreateVoteRequest(
        String title,
        List<String> voteOptions,
        VoteType voteType,
        int durationDays,
        LocalDateTime endTime
) {
}