package site.nomoreparties.stellarburgers.api.authregister;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.AUTH_REGISTER;

public class AuthRegisterClient {
    @Step("Регистрация юзера")
    public static void registerUser(RegisterRequest requestBody) {
        sendRequestRegisterUser(requestBody)
                .then().assertThat().statusCode(SC_OK);

    }

    @Step("Отправка запроса на регистрацию юзера")
    public static Response sendRequestRegisterUser(RegisterRequest requestBody) {
        return given()
                .body(requestBody)
                .when()
                .post(AUTH_REGISTER);

    }
}
