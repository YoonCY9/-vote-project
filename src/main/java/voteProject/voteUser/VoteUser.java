package voteProject.voteUser;

import jakarta.persistence.*;
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
