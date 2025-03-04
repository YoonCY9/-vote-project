package voteProject.voteOption;

import org.springframework.web.bind.annotation.RestController;
import voteProject.voteUser.VoteUserService;

@RestController
public class VoteOptionController {

    private final VoteUserService voteUserService;

    public VoteOptionController(VoteUserService voteUserService) {
        this.voteUserService = voteUserService;
    }
}
