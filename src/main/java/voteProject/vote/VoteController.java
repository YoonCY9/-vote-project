package voteProject.vote;

import org.springframework.web.bind.annotation.*;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteDetailResponse;
import voteProject.vote.voteDTO.VoteFindResponse;
import voteProject.vote.voteDTO.VoteResponse;

import java.util.List;

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
        return voteService.findAll(title);
    }


    @PostMapping("/votes")
    public VoteResponse create(@RequestBody CreateVoteRequest createVoteRequest){
        return voteService.create(createVoteRequest);
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
