package voteProject.voteRecord;

public record VoteRecordResponse(Long userId,
                                 Long voteId,
                                 Long voteOptionId) {

    // 사용자 ID가 null일 수 있음을 처리하는 방법
    public VoteRecordResponse {
        // 익명 투표일 경우 userId는 null로 설정할 수 있도록 처리
        if (userId == null) {
            System.out.println("익명 투표입니다.");
        }
    }
}