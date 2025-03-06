package voteProject.voteRecord;

import java.util.List;

public record VoteRecordRequest(
        Long voteId,
        Long voteUserId,
        List<Long> voteOptionIdList,
        boolean isAnonymous
) {
}