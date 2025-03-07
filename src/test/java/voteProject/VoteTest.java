package voteProject;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import voteProject.vote.VoteType;
import voteProject.vote.voteDTO.CreateVoteRequest;
import voteProject.vote.voteDTO.VoteResponse;
import voteProject.voteRecord.VoteRecordRequest;
import voteProject.voteRecord.VoteRecordResponse;
import voteProject.voteUser.CreateVoteUserRequest;
import voteProject.voteUser.VoteUserRequest;

import java.time.LocalDateTime;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

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
        VoteResponse 투표1 = given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        false
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
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        false
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
                .queryParam("title", "저메")
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
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        false
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
                .pathParam("voteId", 투표1.voteId())
                .when()
                .get("/votes/{voteId}")
                .then().log().all()
                .statusCode(200);
    }

    @Test
    void 투표생성및익명투표테스트() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteUserRequest("닉네임1", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


        VoteResponse voteResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        true
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
                .body(new CreateVoteUserRequest("닉네임2", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new VoteRecordRequest(voteResponse.voteId(), 2L, List.of(1L), true))
                .when()
                .post("/voteRecords")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 투표생성및비익명투표테스트() {

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteUserRequest("닉네임1", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());


        VoteResponse voteResponse = given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        false
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
                .body(new CreateVoteUserRequest("닉네임2", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new VoteRecordRequest(voteResponse.voteId(), 2L, List.of(1L), false))
                .when()
                .post("/voteRecords")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }

    @Test
    void 유저생성투표생성및비익명단일투표_예외테스트() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteUserRequest("닉네임", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.SINGLE,
                        1,
                        false
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
                .body(new VoteRecordRequest(1L, 1L, List.of(1L, 3L, 4L), false))
                .when()
                .post("/voteRecords")
                .then().log().all()
                .statusCode(400)
                .body("message", equalTo("복수응답을 허용하지 않는 투표입니다. 하나의 옵션만 선택해 주세요"));
    }

    @Test
    void 유저생성투표생성및비익명중복투표_예외테스트() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteUserRequest("닉네임", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());

        given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteRequest(
                        1L,
                        "저메추",
                        List.of("중국집", "한식", "일식", "양식"),
                        VoteType.MULTIPLE_MAX_TWO,
                        1,
                        false
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
                .body(new VoteRecordRequest(1L, 1L, List.of(1L, 3L, 4L), false))
                .when()
                .post("/voteRecords")
                .then().log().all()
                .statusCode(400)
                .body("message", equalTo("옵션은 두 개까지 선택 가능합니다."));
    }


}