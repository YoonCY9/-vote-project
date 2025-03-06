package voteProject.vote.voteDTO;

import java.time.LocalDateTime;
import java.util.List;

public record VoteDetailResponse (
        Long voteId,
        String title,
        List<VoteOptionDetailResponse> voteContent,
        LocalDateTime startTime,
        LocalDateTime endTime
){
}
