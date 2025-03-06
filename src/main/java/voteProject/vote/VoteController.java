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
            @RequestParam(required = false) String title) {
        return voteService.searchVoteDetail(title);
    }


    @PostMapping("/votes/{userid}")
    public VoteResponse create(@RequestBody CreateVoteRequest createVoteRequest, @PathVariable Long userid){
        return voteService.create(createVoteRequest, userid);
    }


    @GetMapping("/votes/{voteId}")
    public VoteDetailResponse findByVoteId(@PathVariable Long voteId) {
        return voteService.findByVoteId(voteId);
    }

    @DeleteMapping("/votes/{voteId}")
    public void delete(@PathVariable Long voteId) {
        voteService.delete(voteId);
    }


}
