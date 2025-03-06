package voteProject.vote.voteDTO;

import voteProject.vote.VoteType;
import voteProject.voteOption.VoteOption;

import java.time.LocalDateTime;
import java.util.List;

public record VoteDetailResponse(
        String title,
        List<VoteOption> options,
        VoteType voteType,
        LocalDateTime createAt,
        LocalDateTime endTime
) {
}