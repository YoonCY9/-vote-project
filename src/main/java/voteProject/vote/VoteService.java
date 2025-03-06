package voteProject.vote;

import org.springframework.stereotype.Service;
import voteProject.vote.voteDTO.*;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VoteService(VoteRepository voteRepository, VoteOptionRepository voteOptionRepository, VoteOptionRepository voteOptionRepository1) {
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository1;
    }

    //Todo 투표 상세 조회 (id 와 포함하는 글자로만)
    public List<VoteFindResponse> searchVoteDetail(String title, Long startDate, Long endDate) {

        if (title != null) {
            return voteRepository.findByTitleContaining(title)
                    .stream()
                    .map(v -> new VoteFindResponse(
                            v.getId(),
                            v.getTitle(),
                            v.getTotalVote(),
                            v.getCreateAt(),
                            v.getCreateAt().plusDays(v.getDurationDays())
                    )).toList();
        }

        if (startDate != null) {
            return voteRepository.findByCreateAtAfter(LocalDateTime.now()
                            .minusDays(startDate))
                    .stream()
                    .map(v -> new VoteFindResponse(
                            v.getId(),
                            v.getTitle(),
                            v.getTotalVote(),
                            v.getCreateAt(),
                            v.getCreateAt().plusDays(v.getDurationDays())
                    )).toList();
        }
        if (endDate != null) {

            return voteRepository.findByEndDate(LocalDateTime.now()
                            .plusDays(endDate))
                    .stream()
                    .map(v -> new VoteFindResponse(
                            v.getId(),
                            v.getTitle(),
                            v.getTotalVote(),
                            v.getCreateAt(),
                            v.getCreateAt().plusDays(v.getDurationDays())
                    )).toList();
        }
        return voteRepository.findAll()
                .stream()
                .map(v -> new VoteFindResponse(
                        v.getId(),
                        v.getTitle(),
                        v.getTotalVote(),
                        v.getCreateAt(),
                        v.getCreateAt().plusDays(v.getDurationDays())
                )).toList();


    }

    public VoteResponse create(CreateVoteRequest createVoteRequest) {
        Vote vote = voteRepository.save(new Vote(
                createVoteRequest.title(),
                createVoteRequest.endTime(),
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
                vote.getCreateAt(),
                vote.getCreateAt().plusDays(vote.getDurationDays())
        );

    }
//    //Todo 상세 조회 포함하는 날짜(?create = value) / 종료 날짜 (? endTime = value)
//    public List<VoteDetailResponse> searchVoteByDate(Long startDate, Long endDate) {
//
//    }



    public VoteDetailResponse findByVoteId(Long voteId) {
        Vote vote = voteRepository.findById(voteId).orElseThrow(() ->
                new NoSuchElementException("존재하지 않는 voteId" + voteId));

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
}
