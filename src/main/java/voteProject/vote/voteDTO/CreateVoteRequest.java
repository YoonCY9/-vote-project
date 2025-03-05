package voteProject.vote.voteDTO;

import java.time.LocalDateTime;
import java.util.List;

public record CreateVoteRequest(
        String title,
        List<String> voteContent,
        LocalDateTime startTime,
        LocalDateTime endTime
) { }
