package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import voteProject.vote.voteDTO.VoteDetailResponse;

import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteResponse;


import java.util.List;

@RestController
public class VoteController {
    private final VoteService voteService;


    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    //조회
    @GetMapping("/api/votes")
    public List<VoteDetailResponse> getVoteDetail(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long startDate,
            @RequestParam(required = false) Long endDate) {
        return voteService.searchVoteDetail(title,startDate,endDate);
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

}
