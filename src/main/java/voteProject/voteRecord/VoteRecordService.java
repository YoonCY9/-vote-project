package voteProject.voteRecord;

import org.springframework.stereotype.Service;
import voteProject.vote.Vote;
import voteProject.vote.VoteRepository;
import voteProject.vote.VoteType;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;
import voteProject.voteUser.VoteUser;
import voteProject.voteUser.VoteUserRepository;

import java.util.ArrayList;
import java.util.List;

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

        VoteUser voteUser = voteUserRepository.findById(request.userId()).orElseThrow(
                () -> new IllegalArgumentException("투표에 참여하려면 회원가입이 필요합니다.")
        );

        Vote vote = voteRepository.findById(request.voteId()).orElseThrow(
                () -> new IllegalArgumentException("개설되지 않은 투표입니다.")
        );

        //단일 선택 투표일 때 검증
        if(vote.getVoteType().equals(VoteType.SINGLE) && request.voteOptionIdList().size() > 1){
            throw new IllegalStateException("단일 선택 투표에서는 하나의 옵션만 선택 가능합니다.");
        }

        List<VoteRecordResponse> responses = new ArrayList<>();
        for (Long optionId : request.voteOptionIdList()) {
            if(voteRecordRepository.findByVoteUserIdAndVoteIdAndVoteOptionId(
                    request.userId(), request.voteId(), optionId).isPresent()){
                throw new IllegalStateException("이미 해당 옵션에 투표하였습니다.");
            }

            VoteOption voteOption = voteOptionRepository.findById(optionId).orElseThrow(
                    () -> new IllegalArgumentException("해당 옵션이 존재하지 않습니다.")
            );

            VoteRecord voteRecord = new VoteRecord(vote, voteUser, voteOption);
            voteRecordRepository.save(voteRecord);

            responses.add(new VoteRecordResponse(vote.getId(), voteUser.getId(), voteOption.getId()));
        }
        return responses;
    }
}
