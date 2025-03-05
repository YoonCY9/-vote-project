package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteResponse;

import java.util.List;

@RestController
public class VoteController {

    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/votes")
    public VoteResponse create(@RequestBody CreateVoteRequest createVoteRequest){
        return voteService.create(createVoteRequest);
    }
}
