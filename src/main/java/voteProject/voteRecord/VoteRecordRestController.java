package voteProject.voteRecord;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoteRecordRestController {

    private final VoteRecordService voteRecordService;

    public VoteRecordRestController(VoteRecordService voteRecordService) {
        this.voteRecordService = voteRecordService;
    }

    @PostMapping("voteRecords")
    public List<VoteRecordResponse> castVote(@RequestBody VoteRecordRequest request){
        return voteRecordService.voting(request);
    }

    @GetMapping("/{voteId}")
    public List<String> findNickNameList(@PathVariable Long voteId, @RequestParam(required = false) Long optionId) {
        return voteRecordService.findNickNameList(voteId, optionId);
    }




}
