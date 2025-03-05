package voteProject.vote;

import jakarta.persistence.*;
import org.apache.tomcat.util.net.NioEndpoint;
import org.springframework.data.annotation.CreatedDate;
import voteProject.voteOption.VoteOption;
import voteProject.voteRecord.VoteRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //투표 제목
    @Column(nullable = false)
    private String title;

    // 투표 기록
    @OneToMany(mappedBy = "vote")
    private List<VoteRecord> voteRecords;

    @OneToMany(mappedBy = "vote")
    private List<VoteOption> options = new ArrayList<>();

//    //유저
//    @ManyToOne
//    private VoteUser voteUser;

    //총 투표수
    @Column(nullable = true)
    private Long totalVote;

    @Column(nullable = false)
    @OneToMany
    List<VoteOption> voteOption;
    //생성 날짜
    @CreatedDate
    private LocalDateTime createAt;

    //투표 진행 / 종료 상태
    @Column(nullable = false)
    private boolean isClose = false;

    //종료 일시
    @Column(nullable = false)
    private LocalDateTime endDate;

    public Vote() {
    }

    public Vote(String title, LocalDateTime createAt, LocalDateTime endDate) {
        this.title = title;
        this.createAt = createAt;
        this.endDate = endDate;
    }

    public Vote(Long id,
                String title,
                List<VoteRecord> voteRecords,
                List<VoteOption> voteOption,
                Long totalVote,
                LocalDateTime createAt,
                boolean isClose,
                LocalDateTime endDate) {
        Id = id;
        this.title = title;
        this.voteRecords = voteRecords;
        this.voteOption = voteOption;
        this.totalVote = totalVote;
        this.createAt = createAt;
        this.isClose = isClose;
        this.endDate = endDate;

    }

    public Long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public List<VoteOption> getVoteOption() {
        return voteOption;
    }

    public Long getTotalVote() {
        return totalVote;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public boolean isClose() {
        return isClose;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public List<VoteRecord> getVoteRecords() {
        return voteRecords;
    }

    public List<VoteOption> getOptions() {
        return options;
    }

    public void addOption(List<VoteOption> voteOptions) {
        for (VoteOption option : voteOptions) {
            options.add(option);
            option.setVote(this);
        }
    }

}
