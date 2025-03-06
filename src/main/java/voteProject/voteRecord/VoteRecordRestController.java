package voteProject.voteRecord;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import voteProject.vote.VoteService;

import java.util.List;

@RestController
public class VoteRecordRestController {

    private final VoteRecordService voteRecordService;

    public VoteRecordRestController(VoteRecordService voteRecordService) {
        this.voteRecordService = voteRecordService;
    }

    @PostMapping("voteRecords")
    public VoteRecordResponse castVote(@RequestBody VoteRecordRequest request){
        return voteRecordService.voting(request);
    }


}
