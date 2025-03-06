package voteProject.voteRecord;

import org.springframework.stereotype.Service;
import voteProject.vote.Vote;
import voteProject.vote.VoteRepository;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;
import voteProject.voteUser.VoteUser;
import voteProject.voteUser.VoteUserRepository;

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

    public VoteRecordResponse voting(VoteRecordRequest request){

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

        VoteOption voteOption = voteOptionRepository.findById(request.voteOptionId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 옵션입니다.")
        );

        if(voteRecordRepository.findByVoteUserIdAndVoteIdAndVoteOptionId(request.voteUserId(), request.voteId(), request.voteOptionId()).isPresent()){
            throw new IllegalStateException("이미 투표에 참여하셨습니다.");
        }

        VoteRecord voteRecord = new VoteRecord(vote, voteUser, voteOption);

        voteRecordRepository.save(voteRecord);

        return new VoteRecordResponse(
                vote.getId(),
                isAnonymous ? null : voteUser.getId(),
                voteOption.getId()
        );

    }

}
