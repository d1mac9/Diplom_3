package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;
import site.nomoreparties.stellarburgers.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static site.nomoreparties.stellarburgers.api.ApiHelpers.deleteUser;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.LOGIN_PATH;
import static site.nomoreparties.stellarburgers.helpers.Driver.waitForUrlContains;
import static site.nomoreparties.stellarburgers.helpers.UserGenerator.*;

public class RegisterTest extends BaseTest {
    private RegisterRequest body;

    @BeforeEach
    public void createTestData(){
        body = generateUser();
    }
    @AfterEach
    public void deleteTestData(){
        if (body != null){
            deleteUser(body);
        }
    }
    @Test
    public void shouldUserRegisterSuccessWithPassword6() {
        open(BASE_URL, MainPage.class)
                .clickBtnLogin()
                .clickBtnRegister()
                .setUserName(body.getName())
                .setEmail(body.getEmail())
                .setPassword(body.getPassword())
                .clickBtnRegister();
        waitForUrlContains(BASE_URL + LOGIN_PATH);
    }
    @ParameterizedTest
    @ValueSource(strings = {"12345", "1"})
    public void shouldNotRegisterUserWithPasswordLess6(String password){
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
