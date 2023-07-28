package site.nomoreparties.stellarburgers.api;

import io.qameta.allure.Step;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;

import static site.nomoreparties.stellarburgers.api.login.LoginClient.loginUserAndGetAccessToken;
import static site.nomoreparties.stellarburgers.api.user.UserClient.deleteUserByAccessToken;

public class ApiHelpers {
    @Step("Удаление юзера")
    public static void deleteUser(RegisterRequest body){
        String accessToken = loginUserAndGetAccessToken(body);
        deleteUserByAccessToken(accessToken);
    }
}
