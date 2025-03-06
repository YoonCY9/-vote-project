package voteProject.vote;

import jakarta.persistence.*;
import org.apache.tomcat.util.net.NioEndpoint;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import voteProject.voteOption.VoteOption;
import voteProject.voteRecord.VoteRecord;

import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Column(nullable = true)
    private Long totalVote = 0L;

    @Column(nullable = false)
    @OneToMany(mappedBy = "vote")
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

    public Vote() {
    }

    public Vote(String title, VoteType voteType, LocalDateTime endDate, int durationDays) {
        this.title = title;
        this.voteType = voteType;
        this.endDate = endDate;
        this.durationDays = durationDays;
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

    public int getDurationDays() {
        return durationDays;
    }

    public VoteType getVoteType() {
        return voteType;
    }

    public void setVoteType(VoteType voteType) {
        this.voteType = voteType;
    }

    public void validateOptionCount(List<Long> requestOptionId){

        if(voteType.equals(VoteType.SINGLE) && requestOptionId.size() > 1){
            throw new IllegalStateException("복수응답을 허용하지 않는 투표입니다. 하나의 옵션만 선택해 주세요");
        }

        if(voteType.equals(VoteType.MULTIPLE_EXACTLY_TWO) && requestOptionId.size() != 2){
            throw new IllegalStateException("옵션을 두 개 선택 해 주세요.");
        }

        if(voteType.equals(VoteType.MULTIPLE_MAX_TWO) && requestOptionId.size() > 2){
            throw new IllegalStateException("옵션은 두개까지 선택 가능합니다.");
        }

        if(voteType.equals(VoteType.MULTIPLE_EXACTLY_THREE) && requestOptionId.size() != 3){
            throw new IllegalStateException("옵션을 세 개 선택 해 주세요.");
        }

        if(voteType.equals(VoteType.MULTIPLE_MAX_THREE) && requestOptionId.size() > 3){
            throw new IllegalStateException("옵션은 세 개까지 선택 가능합니다.");
        }
    }
}
