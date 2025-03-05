package voteProject.vote.voteDTO;

import voteProject.voteOption.VoteOptionRequest;
import java.time.LocalDateTime;
import java.util.List;

public record CreateVoteRequest(
        String title,
        List<VoteOptionRequest> options,
        LocalDateTime createAt,
        LocalDateTime endTime
) {
}
        List<String> voteContent,
        LocalDateTime startTime,
        LocalDateTime endTime
) { }
