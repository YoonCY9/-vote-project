package voteProject.voteUser;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteUserRestController {

    private final VoteUserService voteUserService;

    public VoteUserRestController(VoteUserService voteUserService) {
        this.voteUserService = voteUserService;
    }
}
