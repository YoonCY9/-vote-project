package voteProject.vote;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import voteProject.DatabaseCleanup;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteResponse;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VoteTest {
    @LocalServerPort
    int port;

    @Autowired
    DatabaseCleanup databaseCleanup;

    @BeforeEach
    void setUp() {
        databaseCleanup.execute();
        RestAssured.port = port;
    }

    @Test
    void 투표생성() {
        VoteResponse 투표1 = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        LocalDateTime.now(),
                        1
                ))
                .when()
                .post("/votes")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(VoteResponse.class);
    }

    @Test
    void 투표목록조회() {
        RestAssured
           .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        LocalDateTime.now(),
                        1
                ))
                .when()
                .post("/votes")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(VoteResponse.class);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .queryParam("title","저메")
                .when()
                .get("/votes")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void 투표상세조회() {
        VoteResponse 투표1 = RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        LocalDateTime.now(),
                        1
                ))
                .when()
                .post("/votes")
                .then().log().all()
                .statusCode(200)
                .extract()
                .as(VoteResponse.class);

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("voteId",투표1.voteId())
                .when()
                .get("/votes/{voteId}")
                .then().log().all()
                .statusCode(200);

    }
}
