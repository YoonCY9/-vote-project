package voteProject.voteUser;

import jakarta.validation.constraints.NotBlank;

public record CreateVoteUserRequest(

        @NotBlank String nickname,
        @NotBlank String password


) {
}
