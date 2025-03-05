package voteProject.vote;

import org.springframework.stereotype.Service;
import voteProject.vote.voteDTO.*;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VoteService(VoteRepository voteRepository, VoteOptionRepository voteOptionRepository, VoteOptionRepository voteOptionRepository1) {
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository1;
    }

    //Todo 투표 상세 조회 (id 와 포함하는 글자로만)
    public List<VoteDetailResponse> searchVoteDetail(String title, Long id, Long startDate, Long endDate) {


        if (title != null) {
            return voteRepository.findByContaningTitle(title)
                    .stream()
                    .map(vote -> new VoteDetailResponse(
                            vote.getTitle(),
                            vote.getVoteOption(),
                            vote.getCreateAt(),
                            vote.getEndTime())).toList();
        }
        if (id != null) {
            return voteRepository.findById(id)
                    .map(vote -> List.of(new VoteDetailResponse(
                                    vote.getTitle(),
                                    vote.getVoteOption(),
                                    vote.getCreateAt(),
                                    vote.getEndTime()
                            )
                    ))
                    .orElseThrow(() -> new IllegalArgumentException("찾을수 없는 id"));
        }
        if (startDate != null) {
            return voteRepository.findByCreateAtAfter(LocalDateTime.now()
                            .minusDays(startDate))
                    .stream()
                    .map(vote -> new VoteDetailResponse(
                            vote.getTitle(),
                            vote.getVoteOption(),
                            vote.getCreateAt(),
                            vote.getEndTime()
                    )).toList();
        }
        if (endDate != null) {

            return voteRepository.findByEndDate(LocalDateTime.now()
                            .plusDays(endDate))
                    .stream()
                    .map(vote -> new VoteDetailResponse(
                            vote.getTitle(),
                            vote.getVoteOption(),
                            vote.getCreateAt(),
                            vote.getEndTime()
                    )).toList();
        }
        return voteRepository.findAll()
                .stream()
                .map(vote -> new VoteDetailResponse(
                        vote.getTitle(),
                        vote.getVoteOption(),
                        vote.getCreateAt(),
                        vote.getEndTime()
                )).toList();
    }

    public VoteResponse create(CreateVoteRequest createVoteRequest) {
        Vote vote = voteRepository.save(new Vote(
                createVoteRequest.title(),
                LocalDateTime.now(),
                createVoteRequest.endTime()
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
                vote.getEndTime()
        );

    }
//    //Todo 상세 조회 포함하는 날짜(?create = value) / 종료 날짜 (? endTime = value)
//    public List<VoteDetailResponse> searchVoteByDate(Long startDate, Long endDate) {
//
//    }

    // 투표 전체조회
    public VoteListResponse findAll() {
        List<Vote> votes = voteRepository.findAll();

        List<VoteFindResponse> voteDTO = votes.stream().map(v -> new VoteFindResponse(
                v.getId(),
                v.getTitle(),
                v.getTotalVote(),
                v.getCreateAt(),
                v.getEndTime()
        )).toList();

        return new VoteListResponse(voteDTO);
    }
}
