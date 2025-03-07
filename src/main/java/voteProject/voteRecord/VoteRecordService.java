package voteProject.voteRecord;

import org.springframework.stereotype.Service;
import voteProject.vote.Vote;
import voteProject.vote.VoteRepository;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;
import voteProject.voteUser.VoteUser;
import voteProject.voteUser.VoteUserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoteRecordService {

    private final VoteRecordRepository voteRecordRepository;
    private final VoteUserRepository voteUserRepository;
    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VoteRecordService(VoteRecordRepository voteRecordRepository, VoteUserRepository voteUserRepositoddry, VoteUserRepository voteUserRepository, VoteOptionRepository voteOptionRepository, VoteRepository voteRepository, VoteOptionRepository voteOptionRepository1) {
        this.voteRecordRepository = voteRecordRepository;
        this.voteUserRepository = voteUserRepository;
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository1;
    }

    public List<VoteRecordResponse> voting(VoteRecordRequest request){

        boolean isAnonymous = request.isAnonymous();

        VoteUser voteUser = null;

        if (!isAnonymous) {
            voteUser  = voteUserRepository.findById(request.voteUserId()).orElseThrow(
                    () -> new IllegalArgumentException("투표에 참여하려면 회원가입이 필요합니다.")
            );
        }

        Vote vote = voteRepository.findById(request.voteId()).orElseThrow(
                () -> new IllegalArgumentException("개설되지 않은 투표입니다.")
        );

        // 삭제 됐는지? 삭제됐다면 오류 발생
        vote.isDeleted();

        //voteType에 맞는 옵션 선택인지 검증
        vote.validateOptionCount(request.voteOptionIdList());

        List<VoteRecordResponse> responses = new ArrayList<>();

        for (Long optionId : request.voteOptionIdList()) {
            // 중복 투표 방지
            if (voteRecordRepository.findByVoteUserIdAndVoteIdAndVoteOptionId(
                    request.voteUserId(), request.voteId(), optionId).isPresent()) {
                throw new IllegalStateException("이미 같은 옵션에 투표했습니다.");
            }

            VoteOption voteOption = voteOptionRepository.findById(optionId).orElseThrow(
                    () -> new IllegalArgumentException("존재하지 않는 옵션입니다.")
            );

            VoteRecord voteRecord = new VoteRecord(vote, voteUser, voteOption);
            voteRecordRepository.save(voteRecord);

            responses.add(new VoteRecordResponse(vote.getId(), isAnonymous ? null : voteUser.getId(), voteOption.getId()));
        }
        return responses;
    }

    // voteid로 보트찾고 그 보트에 해당하는 옵션리스트 찾고 옵션하나에 대한 name 리스트 반환
    public List<String> findNickNameList(Long voteId, Long optionId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 투표id" + voteId));

        List<VoteRecord> records = voteRecordRepository.findByVoteIdAndVoteOptionId(voteId, optionId);

        return records.stream().map(r -> r.getVoteUser().getNickname()).toList();
    }



}
