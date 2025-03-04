package voteProject.vote;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import voteProject.voteOption.VoteOption;
import voteProject.voteRecord.VoteRecord;
import voteProject.voteUser.VoteUser;

import java.time.LocalDateTime;
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

//    //유저
//    @ManyToOne
//    private VoteUser voteUser;

    //총 투표수
    @Column(nullable = true)
    private Long totalVote;


    //생성 날짜
    @CreatedDate
    private LocalDateTime createAt;

    //투표 진행 / 종료 상태
    @Column(nullable = false)
    private boolean isClose = false;

    //종료 일시
    @Column(nullable = false)
    private LocalDateTime endTime;

    public Vote(Long id,
                String title,
                List<VoteRecord> voteRecords,
                Long totalVote,
                LocalDateTime createAt,
                boolean isClose,
                LocalDateTime endTime)
    {
        Id = id;
        this.title = title;
        this.voteRecords = voteRecords;
        this.totalVote = totalVote;
        this.createAt = createAt;
        this.isClose = isClose;
        this.endTime = endTime;
    }

    public Long getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public List<VoteRecord> getVoteRecords() {
        return voteRecords;
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

    public LocalDateTime getEndTime() {
        return endTime;
    }

}
