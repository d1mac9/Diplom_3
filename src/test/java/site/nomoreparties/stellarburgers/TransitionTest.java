package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;
import site.nomoreparties.stellarburgers.pageobject.BasePage;
import site.nomoreparties.stellarburgers.pageobject.LkPage;

import static com.codeborne.selenide.Selenide.*;
import static site.nomoreparties.stellarburgers.api.ApiHelpers.deleteUser;
import static site.nomoreparties.stellarburgers.api.authregister.AuthRegisterClient.registerUser;
import static site.nomoreparties.stellarburgers.api.login.LoginClient.loginUserAndGetAccessToken;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.ACCOUNT_PROFILE;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.HeaderLabelName.CONSTRUCTOR;
import static site.nomoreparties.stellarburgers.constants.HeaderLabelName.LK;
import static site.nomoreparties.stellarburgers.helpers.UiHelpers.checkUrl;
import static site.nomoreparties.stellarburgers.helpers.UserGenerator.generateUser;

@DisplayName("Переходы пользователя")
public class TransitionTest extends BaseTest {
    RegisterRequest body;
    String accessToken;

    @BeforeEach
    public void createTestData() {
        body = generateUser();
        registerUser(body);
        accessToken = loginUserAndGetAccessToken(body);

        open(BASE_URL, BasePage.class)
                .addTokenToLocalStorage(accessToken)
                .clickBtnLabel(LK.getHeaderName());
    }

    @AfterEach
    public void deleteTestData() {
        if (body != null) {
            deleteUser(body);
        }
    }

    @DisplayName("Открытие страницы личного кабинета для авторизованного пользователя")
    @Test
    public void shouldOpenLkWithLoginParamsWhenUserIsAuthorized() {
        page(LkPage.class)
                .checkLkTabIsActive("Профиль");
        checkUrl(BASE_URL + ACCOUNT_PROFILE);
    }

    @DisplayName("Переход в конструктор при нажатии на лейбл")
    @Test
    public void shouldOpenConstructorPageByClicking(){
        page(BasePage.class)
                .clickOnMainLabel()
                .checkLabelIsActive(CONSTRUCTOR.getHeaderName());
        checkUrl(BASE_URL);
    }
    @DisplayName("Переход в конструктор при нажатии на соответствующий пункт в заголовке")
    @Test
    public void shouldOpenConstructorPageByClickingConstructor(){
        page(BasePage.class)
                .clickBtnLabel(CONSTRUCTOR.getHeaderName())
                .checkLabelIsActive(CONSTRUCTOR.getHeaderName());
        checkUrl(BASE_URL);
    }
}
