package voteProject.vote;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VoteQRepository {

    private final JPAQueryFactory jpaQueryFactory;
    private final QVote vote = QVote.vote;

    public VoteQRepository(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<Vote> findAll(String title) {
        return jpaQueryFactory
                .selectFrom(vote)
                .where(
                        vote.isDeleted.isFalse(),
                        containsTitle(title))
                .fetch();
    }

    public BooleanExpression containsTitle(String title) {
        if (title == null) {
            return null;
        } else
            return vote.title.containsIgnoreCase(title);
    }
}
