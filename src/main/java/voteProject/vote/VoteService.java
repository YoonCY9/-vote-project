package voteProject.vote;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import voteProject.vote.voteDTO.*;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;
import voteProject.voteUser.VoteUser;
import voteProject.voteUser.VoteUserRepository;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;
    private final VoteUserRepository voteUserRepository;
    private final VoteQRepository voteQRepository;

    public VoteService(VoteRepository voteRepository, VoteOptionRepository voteOptionRepository, VoteUserRepository voteUserRepository, VoteQRepository voteQRepository) {
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository;
        this.voteUserRepository = voteUserRepository;
        this.voteQRepository = voteQRepository;
    }

    public VoteResponse create(CreateVoteRequest createVoteRequest) {
        VoteUser voteUser = voteUserRepository.findById(createVoteRequest.userId()).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 user Id " + createVoteRequest.userId()));

        Vote vote = voteRepository.save(new Vote(
                voteUser,
                createVoteRequest.title(),
                createVoteRequest.voteType(),
                createVoteRequest.durationDays()
        ));

        List<VoteOption> voteOptions = voteOptionRepository.saveAll(createVoteRequest.voteOptions()
                .stream()
                .map(option -> new VoteOption(option, vote))
                .toList());

        List<VoteOptionResponse> optionContents = voteOptions.stream()
                .map(option ->
                        new VoteOptionResponse(option.getId(),
                                option.getContent()))
                .toList();

        return new VoteResponse(
                vote.getId(),
                vote.getTitle(),
                optionContents,
                vote.getVoteType(),
                vote.getCreateAt(),
                vote.getCreateAt().plusDays(vote.getDurationDays())
        );

    }

    public List<VoteFindResponse> findAll(String title) {

        List<Vote> votes = voteQRepository.findAll(title);

        return votes.stream().map(
                v -> new VoteFindResponse(
                        v.getId(),
                        v.getTitle(),
                        v.getTotalVote(),
                        v.getCreateAt(),
                        v.getCreateAt().plusDays(v.getDurationDays())
        )).toList();

    }

    public VoteDetailResponse findByVoteId(Long voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 voteId" + voteId));

        vote.isDeleted();

        Long totalVote = vote.getTotalVote();

        List<VoteOption> voteOptions = voteOptionRepository.findByVoteId(vote.getId());

        List<VoteOptionDetailResponse> optionResponseList = voteOptions.stream()
                .map(v -> new VoteOptionDetailResponse(
                        v.getId(),
                        v.getContent(),
                        v.getCount(),
                        v.votePercentage(totalVote)
                )).toList();

        return new VoteDetailResponse(
                vote.getId(),
                vote.getTitle(),
                optionResponseList,
                vote.getCreateAt(),
                vote.getCreateAt().plusDays(vote.getDurationDays()));
    }

    @Transactional
    public void delete(Long voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 voteId" + voteId));

        vote.deleteVote();
    }
}
