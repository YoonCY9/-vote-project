package voteProject.vote.voteDTO;

import voteProject.voteOption.VoteOption;

import java.time.LocalDateTime;
import java.util.List;

public record VoteDetailResponse(
        String title,
        List<VoteOption> options,
        LocalDateTime createAt,
        LocalDateTime endTime
) {
}