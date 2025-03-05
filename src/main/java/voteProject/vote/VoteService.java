package voteProject.vote;

import org.springframework.stereotype.Service;
import voteProject.vote.voteDTO.VoteDetailResponse;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    public VoteService(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;

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
//    //Todo 상세 조회 포함하는 날짜(?create = value) / 종료 날짜 (? endTime = value)
//    public List<VoteDetailResponse> searchVoteByDate(Long startDate, Long endDate) {
//
//    }
}
