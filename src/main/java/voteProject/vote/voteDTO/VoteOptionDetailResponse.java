package voteProject.vote.voteDTO;

public record VoteOptionDetailResponse(
        Long optionId,
        String content,
        int count,
        double percentage ){

}

