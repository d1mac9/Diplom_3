package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;
import site.nomoreparties.stellarburgers.page_object.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static site.nomoreparties.stellarburgers.api.ApiHelpers.deleteUser;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.LOGIN_PATH;
import static site.nomoreparties.stellarburgers.helpers.Driver.waitForUrlContains;
import static site.nomoreparties.stellarburgers.helpers.UserGenerator.*;

@DisplayName("Регистрация пользователя")
public class RegisterTest extends BaseTest {
    private RegisterRequest body;

    @AfterEach
    public void deleteTestData() {
        if (body != null) {
            deleteUser(body);
        }
    }

    @DisplayName("Проверка успешной регистрации с паролем 6 символов")
    @Test
    public void shouldUserRegisterSuccessWithPassword6() {
        body = generateUser();
        open(BASE_URL, MainPage.class)
                .clickBtnLogin()
                .clickBtnRegister()
                .setUserName(body.getName())
                .setEmail(body.getEmail())
                .setPassword(body.getPassword())
                .clickBtnRegister();
        waitForUrlContains(BASE_URL + LOGIN_PATH);
    }

    @DisplayName("Проверка неуспешной регистрации с паролем менее 6 символов")
    @ParameterizedTest
    @ValueSource(strings = {"12345", "1"})
    public void shouldNotRegisterUserWithPasswordLess6(String password) {
        open(BASE_URL, MainPage.class)
                .clickBtnLogin()
                .clickBtnRegister()
                .setUserName(generateUsername())
                .setEmail(generateEmail())
                .setPassword(password)
                .clickBtnRegister()
                .checkValidErrorIsVisible();
    }
}
