package voteProject.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import voteProject.vote.voteDTO.VoteDetailResponse;

import java.util.List;

@Controller
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @GetMapping("/api/votes/{id}")
    public List<VoteDetailResponse> getVoteDetail(
            @RequestBody String title,
            @PathVariable Long id) {
        return voteService.searchVoteDetail(title, id);
    }

    @GetMapping("/api/votes/dates")
    public List<VoteDetailResponse> getVoteDetail(
            @RequestParam (required = false) Long startDate,
            @RequestParam (required = false) Long endDate) {
        return voteService.searchVoteByDate(startDate, endDate);
    }
}
