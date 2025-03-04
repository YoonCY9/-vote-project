package voteProject.voteOption;

import jakarta.persistence.*;

@Entity
public class VoteOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column (nullable = false)
    String content;

    int count;

    @ManyToOne
    Vote vote;

    protected VoteOption() {
    }

    public VoteOption(Long id, String content, int count, Vote vote) {
        this.id = id;
        this.content = content;
        this.count = count;
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
