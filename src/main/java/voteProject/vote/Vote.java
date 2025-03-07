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
    private Long totalVote;

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

    @Column
    private LocalDateTime endDate;

    @Column(nullable = false)
    private int durationDays;

    @Column(nullable = false)
    private boolean isDeleted = false;

    // 익명 여부
    @Column(nullable = false)
    private boolean isAnonymous;


    public Vote() {
    }

    public Vote(VoteUser voteUser,
                String title,
                VoteType voteType,
                int durationDays,
                boolean isAnonymous) {
        this.voteUser = voteUser;
        this.title = title;
        this.voteType = voteType;
        this.durationDays = durationDays;
        this.totalVote = 0L;
        this.endDate = LocalDateTime.now().plusDays(this.durationDays);
        this.isAnonymous = isAnonymous;
    }

    // 삭제됐는지?????
    public void isDeleted() {
        if (isDeleted) {
            throw new IllegalArgumentException("삭제된 투표입니다.");
        }

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

    public VoteUser getVoteUser() {
        return voteUser;
      
    public boolean isAnonymous() {
        return isAnonymous;
    }

    public void setAnonymous(boolean anonymous) {
        isAnonymous = anonymous;

    }

    public void deleteVote() {
        this.isDeleted = true;
    }

    public void validateOptionCount(List<Long> requestOptionId) {

        if (voteType.equals(VoteType.SINGLE) && requestOptionId.size() > 1) {
            throw new IllegalStateException("복수응답을 허용하지 않는 투표입니다. 하나의 옵션만 선택해 주세요");
        }

        if (voteType.equals(VoteType.MULTIPLE_EXACTLY_TWO) && requestOptionId.size() != 2) {
            throw new IllegalStateException("옵션을 두 개 선택 해 주세요.");
        }

        if (voteType.equals(VoteType.MULTIPLE_MAX_TWO) && requestOptionId.size() > 2) {
            throw new IllegalStateException("옵션은 두 개까지 선택 가능합니다.");
        }

        if (voteType.equals(VoteType.MULTIPLE_EXACTLY_THREE) && requestOptionId.size() != 3) {
            throw new IllegalStateException("옵션을 세 개 선택 해 주세요.");
        }

        if (voteType.equals(VoteType.MULTIPLE_MAX_THREE) && requestOptionId.size() > 3) {
            throw new IllegalStateException("옵션은 세 개까지 선택 가능합니다.");
        }
    }

    public void sumTotalCount(int count){
        this.totalVote += count;
    }
}
