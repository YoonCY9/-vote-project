package voteProject.voteOption;

import jakarta.persistence.*;
import voteProject.vote.Vote;
import voteProject.voteRecord.VoteRecord;

import java.math.BigDecimal;
import java.math.RoundingMode;
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

    public double votePercentage(Long totalCount) {
        if (totalCount == null || totalCount == 0) return 0.0;
        BigDecimal countBD = new BigDecimal(this.count);
        BigDecimal totalBD = new BigDecimal(totalCount);
        BigDecimal percentage = countBD
                .divide(totalBD, 4, RoundingMode.HALF_UP)  // 소수점 4자리까지 유지
                .multiply(new BigDecimal(100));
        return percentage.setScale(1, RoundingMode.HALF_UP).doubleValue();
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
