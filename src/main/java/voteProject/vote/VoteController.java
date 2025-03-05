package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import voteProject.vote.voteDTO.*;


import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class VoteController {
    private final VoteService voteService;
    private final VoteRepository voteRepository;


    public VoteController(VoteService voteService,
                          VoteRepository voteRepository) {
        this.voteService = voteService;
        this.voteRepository = voteRepository;
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

    @GetMapping("/votes")
    public VoteListResponse findAll() {
        return voteService.findAll();
    }

    @GetMapping("/votes/{voteId}")
    public VoteResponse findByVoteId(@PathVariable Long voteId) {
        return voteService.findByVoteId(voteId);
    }


}
