package voteProject.voteUser;

import org.springframework.stereotype.Service;

@Service
public class VoteUserService {

    private final VoteUserRepository repository;

    public VoteUserService(VoteUserRepository repository) {
        this.repository = repository;
    }
}
