package voteProject.voteUser;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteUserRestController {

    private final VoteUserService voteUserService;

    public VoteUserRestController(VoteUserService voteUserService) {
        this.voteUserService = voteUserService;
    }

    @PostMapping("/voteusers")
    public VoteUserResponse createVoteUser(@RequestBody CreateVoteUserRequest request){
        return voteUserService.createVoteUser(request);
    }




}
