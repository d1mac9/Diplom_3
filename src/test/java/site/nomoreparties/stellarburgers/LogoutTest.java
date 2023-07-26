package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;
import site.nomoreparties.stellarburgers.pageobject.AuthorizationPage;
import site.nomoreparties.stellarburgers.pageobject.BasePage;
import site.nomoreparties.stellarburgers.pageobject.LkPage;
import site.nomoreparties.stellarburgers.pageobject.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.nomoreparties.stellarburgers.api.ApiHelpers.deleteUser;
import static site.nomoreparties.stellarburgers.api.authregister.AuthRegisterClient.registerUser;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.LOGIN_PATH;
import static site.nomoreparties.stellarburgers.constants.HeaderLabelName.LK;
import static site.nomoreparties.stellarburgers.helpers.UiHelpers.checkUrl;
import static site.nomoreparties.stellarburgers.helpers.UserGenerator.generateUser;

@DisplayName("Логаут пользователя")
public class LogoutTest extends BaseTest {
    RegisterRequest body;

    @BeforeEach
    public void createTestData() {
        body = generateUser();
        registerUser(body);
        open(BASE_URL, MainPage.class)
                .clickBtnLogin();
        page(AuthorizationPage.class)
                .setEmail(body.getEmail())
                .setPassword(body.getPassword())
                .clickBtnSubmit();
    }

    @AfterEach
    public void deleteTestData() {
        if (body != null) {
            deleteUser(body);
        }
    }

    @DisplayName("Проверка логаута из личного кабинета")
    @Test
    public void shouldLogoutFromLk() {
        page(BasePage.class)
                .clickBtnLabel(LK.getHeaderName());
        page(LkPage.class)
                .clickLkTab("Выход");
        checkUrl(BASE_URL + LOGIN_PATH);
    }

}
