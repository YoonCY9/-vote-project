package voteProject.voteOption;

import jakarta.persistence.*;
import voteProject.vote.Vote;
import voteProject.voteRecord.VoteRecord;

import java.util.List;

@Entity
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column (nullable = false)
    String content;

    int count;

    @ManyToOne
    private Vote vote;

    @OneToMany(mappedBy = "voteOption")
    private List<VoteRecord> voteRecords;

    protected VoteOption() {
    }

    public VoteOption(Long id, String content, int count, Vote vote) {
        this.id = id;
        this.content = content;
        this.count = count;
        this.vote = vote;
    }

    public VoteOption(String content, Vote vote) {
        this.content = content;
        this.vote = vote;
    }



    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public int getCount() {
        return count;
    }

    public Vote getVote() {
        return vote;
    }
}
