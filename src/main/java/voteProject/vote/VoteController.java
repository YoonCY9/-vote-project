package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import voteProject.vote.voteDTO.VoteDetailResponse;

import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteListResponse;
import voteProject.vote.voteDTO.VoteResponse;


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



    @PostMapping("/votes")
    public VoteResponse create(@RequestBody CreateVoteRequest createVoteRequest){
        return voteService.create(createVoteRequest);
    }

    @GetMapping("/votes")
    public VoteListResponse findAll() {
        return voteService.findAll();
    }

}
