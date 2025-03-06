package voteProject.voteRecord;

import voteProject.voteOption.VoteOption;

import java.util.List;

public record VoteRecordRequest(Long userId,
                                Long voteId,
                                List<Long> voteOptionIdList) {
}
