package vote;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.voteOption.VoteOptionRequest;

import java.time.LocalDateTime;
import java.util.List;

public class VoteDetailResponseTest {
    @Test
    void name() {
//        RestAssured
//                .given().log().all()
//                .contentType(ContentType.JSON)
//                .body(new CreateVoteRequest("음바페"
//                        ,List.of(new VoteOptionRequest("dd"
//                        ,2))
//                        , LocalDateTime.now().plusDays(5)))
//                .when().post("/api/votes")
//                .then().log().all()
//                .statusCode(201);
        RestAssured
                .given().log().all()
                .pathParam("startAt", 1)
                .when()
                .get("/api/votes/dates")
                .then().log().all()
                .statusCode(200);
    }
}
