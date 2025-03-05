package voteProject.vote.voteDTO;



import java.time.LocalDateTime;
import java.util.List;

public record VoteDetailResponse(
        String title,
        List<VoteOptionResponse> options,
        LocalDateTime createAt,
        LocalDateTime endTime
) {
}