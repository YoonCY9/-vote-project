package voteProject.voteRecord;

import jakarta.persistence.*;
import voteProject.vote.Vote;
import voteProject.voteOption.VoteOption;
import voteProject.voteUser.VoteUser;

@Entity
public class VoteRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Vote vote;

    @ManyToOne
    @JoinColumn(name = "vote_user_id")
    private VoteUser voteUser;

    @ManyToOne
    private VoteOption voteOption;

    @Column(nullable = false)
    private boolean isAnonymous;


    protected VoteRecord() {
    }

    public VoteRecord(Vote vote, VoteUser voteUser, VoteOption voteOption) {
        this.vote = vote;
        this.voteUser = voteUser;
        this.voteOption = voteOption;
        this.isAnonymous = isAnonymous;
    }

    public VoteRecord(VoteOption voteOption) {
        this.voteOption = voteOption;
        this.isAnonymous = isAnonymous;
    }

    public Long getId() {
        return id;
    }

    public Vote getVote() {
        return vote;
    }

    public VoteUser getVoteUser() {
        return voteUser;
    }

    public VoteOption getVoteOption() {
        return voteOption;
    }

    public boolean isAnonymous() {
        return isAnonymous;
    }
}
