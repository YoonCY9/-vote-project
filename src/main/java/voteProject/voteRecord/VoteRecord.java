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
    private VoteUser voteUser;

    @ManyToOne
    private VoteOption voteOption;


}
