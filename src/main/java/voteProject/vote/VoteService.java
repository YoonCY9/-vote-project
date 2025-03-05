package voteProject.vote;

import org.springframework.stereotype.Service;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteOptionResponse;
import voteProject.vote.voteDTO.VoteResponse;
import voteProject.voteOption.VoteOption;
import voteProject.voteOption.VoteOptionRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;
    private final VoteOptionRepository voteOptionRepository;

    public VoteService(VoteRepository voteRepository, VoteOptionRepository voteOptionRepository) {
        this.voteRepository = voteRepository;
        this.voteOptionRepository = voteOptionRepository;
    }

    public VoteResponse create(CreateVoteRequest createVoteRequest) {
        Vote vote = voteRepository.save(new Vote(
                createVoteRequest.title(),
                LocalDateTime.now(),
                createVoteRequest.endTime()
        ));

        List<VoteOption> voteOptions = voteOptionRepository.saveAll(createVoteRequest.voteContent()
                .stream()
                .map(option -> new VoteOption(option, vote))
                .toList());

        List<VoteOptionResponse> optionContents = voteOptions.stream()
                .map(option ->
                        new VoteOptionResponse(option.getId(),
                                option.getContent()))
                .toList();

        return new VoteResponse(
                vote.getId(),
                vote.getTitle(),
                optionContents,
                vote.getCreateAt(),
                vote.getEndTime()
        );

    }
}
