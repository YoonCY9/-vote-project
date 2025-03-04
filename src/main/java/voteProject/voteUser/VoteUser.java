package voteProject.voteUser;

import jakarta.persistence.*;
import voteProject.vote.Vote;


@Entity
public class VoteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @ManyToOne
    private Vote vote;

    protected VoteUser() {
    }

    public VoteUser(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}
