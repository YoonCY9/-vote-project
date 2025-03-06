package voteProject.voteRecord;

import voteProject.vote.voteDTO.VoteOptionResponse;

import java.util.List;

public record VoteRecordResponse(Long userId,
                                 Long voteId,
                                 Long voteOptionId) {
}
