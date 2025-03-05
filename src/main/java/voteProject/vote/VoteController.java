package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voteProject.vote.voteDTO.VoteDetailResponse;

import java.util.List;

@RestController
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }
    //상세 조회
    @GetMapping("/api/votes/{id}")
    public List<VoteDetailResponse> getVoteDetail(
            @RequestBody String title,
            @PathVariable Long id,
            @RequestParam Long startDate,
            @RequestParam Long endDate) {
        return voteService.searchVoteDetail(title, id,startDate,endDate);
    }
//
//    @GetMapping("/api/votes/dates")
//    public List<VoteDetailResponse> getVoteDetail(
//            @RequestParam (required = false) Long startDate,
//            @RequestParam (required = false) Long endDate) {
//        return voteService.searchVoteByDate(startDate, endDate);
//    }
}
