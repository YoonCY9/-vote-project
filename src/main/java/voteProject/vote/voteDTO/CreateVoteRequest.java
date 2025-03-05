package voteProject.vote.voteDTO;

import voteProject.voteOption.VoteOptionRequest;
import java.time.LocalDateTime;
import java.util.List;

public record CreateVoteRequest(
        String title,
        List<String> voteOptions,
        LocalDateTime createAt,
        LocalDateTime endTime
) {
}