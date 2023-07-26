package site.nomoreparties.stellarburgers.api.login;

import io.qameta.allure.Step;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.AUTH_LOGIN;

public class LoginClient {
    @Step("Отправка запроса на на получение accessToken юзера")
    public static String loginUserAndGetAccessToken(RegisterRequest body){
        return given()
                .body(body)
                .when()
                .post(AUTH_LOGIN)
                .then().assertThat().statusCode(SC_OK)
                .and()
                .extract()
                .path("accessToken");
    }
}
