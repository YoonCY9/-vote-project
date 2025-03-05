package voteProject.voteUser;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import voteProject.vote.Vote;
import voteProject.voteRecord.VoteRecord;

import java.util.List;


@Entity
public class VoteUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "voteUser")
    private List<VoteRecord> voteRecords;


    protected VoteUser() {
    }

    public VoteUser(Long id, String nickname, String password, List<VoteRecord> voteRecords) {
        this.id = id;
        this.nickname = nickname;
        this.password = password;
        this.voteRecords = voteRecords;
    }

    public VoteUser(String nickname, String password) {
        this.nickname = nickname;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }

    public List<VoteRecord> getVoteRecords() {
        return voteRecords;
    }
}
