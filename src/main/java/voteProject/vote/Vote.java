package voteProject.vote;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import voteProject.voteOption.VoteOption;
import voteProject.voteRecord.VoteRecord;
import voteProject.voteUser.VoteUser;

import java.time.LocalDateTime;
import java.util.List;

@EntityListeners(AuditingEntityListener.class)
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
    @ManyToOne
    private VoteUser voteUser;

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

    @Enumerated(EnumType.STRING)
    private VoteType voteType = VoteType.SINGLE;

    //종료 일시
    @Column(nullable = false)
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private boolean isDeleted = false;

    public Vote() {
    }

    public Vote(VoteUser voteUser, String title, VoteType voteType, LocalDateTime endDate, int durationDays) {
        this.voteUser = voteUser;
        this.title = title;
        this.voteType = voteType;
        this.endDate = endDate;
        this.durationDays = durationDays;
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

    public int getDurationDays() {
        return durationDays;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public void deleteVote() {
        this.isDeleted = true;
    }

}
