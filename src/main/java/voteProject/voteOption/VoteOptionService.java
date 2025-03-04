package voteProject.voteOption;

import org.springframework.stereotype.Service;

@Service
public class VoteOptionService {

    private final VoteOptionRepository voteOptionRepository;

    public VoteOptionService(VoteOptionRepository voteOptionRepository) {
        this.voteOptionRepository = voteOptionRepository;
    }
}
