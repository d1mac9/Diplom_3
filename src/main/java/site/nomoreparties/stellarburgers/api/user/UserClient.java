package site.nomoreparties.stellarburgers.api.user;

import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.AUTH_USER;

public class UserClient {
    @Step("Удаление юзера")
    public static void deleteUserByAccessToken(String accessToken){
        given()
                .header("authorization", accessToken)
                .when()
                .delete(AUTH_USER)
                .then().assertThat().statusCode(SC_ACCEPTED);
    }
}
