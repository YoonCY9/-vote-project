package voteProject;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import voteProject.voteUser.CreateVoteUserRequest;


public class CreateVoteUsetTest extends AcceptanceTest {


    //유저생성테스트
    @DisplayName(" createVoteUser Test")
    @Test
    void createGuardian() {
        RestAssured
                .given().log().all()
                .contentType(ContentType.JSON)
                .body(new CreateVoteUserRequest("닉네임", "486"))
                .when()
                .post("/voteusers")
                .then().log().all()
                .statusCode(HttpStatus.OK.value());
    }


}
