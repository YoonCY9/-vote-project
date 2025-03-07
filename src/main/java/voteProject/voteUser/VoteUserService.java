package voteProject.voteUser;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class VoteUserService {

    private final VoteUserRepository voteUserRepository;

    public VoteUserService(VoteUserRepository repository) {
        this.voteUserRepository = repository;
    }


    @Transactional
    public VoteUserResponse createVoteUser(CreateVoteUserRequest request) {

        boolean voteUserExists = voteUserRepository.existsByNickname(request.nickname());

        if (voteUserExists) {
            throw new IllegalArgumentException("이미 존재하는 닉네임");
        }

        VoteUser voteUser = new VoteUser(
                request.nickname(),
                request.password()
        );

        voteUserRepository.save(voteUser);

        return new VoteUserResponse(
                voteUser.getId(),
                voteUser.getNickname()
        );


    }
}
