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

    //목록 조회
    @GetMapping("/votes")
    public List<VoteFindResponse> getVoteDetail(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long startDate,
            @RequestParam(required = false) Long endDate) {
        return voteService.searchVoteDetail(title,startDate,endDate);
    }


    @PostMapping("/votes")
    public VoteResponse create(@RequestBody CreateVoteRequest createVoteRequest){
        return voteService.create(createVoteRequest);
    }


    @GetMapping("/votes/{voteId}")
    public VoteResponse findByVoteId(@PathVariable Long voteId) {
        return voteService.findByVoteId(voteId);
    }


}
