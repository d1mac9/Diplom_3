package site.nomoreparties.stellarburgers;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import site.nomoreparties.stellarburgers.api.models.RegisterRequest;
import site.nomoreparties.stellarburgers.page_object.AuthorizationPage;
import site.nomoreparties.stellarburgers.page_object.BasePage;
import site.nomoreparties.stellarburgers.page_object.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.page;
import static site.nomoreparties.stellarburgers.api.ApiHelpers.deleteUser;
import static site.nomoreparties.stellarburgers.api.authregister.AuthRegisterClient.registerUser;
import static site.nomoreparties.stellarburgers.constants.ApiUrls.BASE_URL;
import static site.nomoreparties.stellarburgers.constants.HeaderLabelName.LK;
import static site.nomoreparties.stellarburgers.helpers.UserGenerator.generateUser;

@DisplayName("Авторизация пользователя")
public class AuthorizationTest extends BaseTest {
    static RegisterRequest body;
    private final String btnEnterOnMainPage = "вход по кнопке «Войти в аккаунт» на главной";
    private final String btnEnterLk = "вход через кнопку «Личный кабинет»";
    private final String btnEnterOnRegistrationForm = "вход через кнопку в форме регистрации";
    private final String btnEnterOnRecoverPassword = "вход через кнопку в форме восстановления пароля";

    @BeforeEach
    public void createTestData() {
        body = generateUser();
        registerUser(body);
    }

    @AfterEach
    public void deleteTestData() {
        if (body != null) {
            deleteUser(body);
        }
    }

    @DisplayName("Проверка успешного перехода на страницу авторизации из разных мест приложения")
    @ParameterizedTest
    @ValueSource(strings = {btnEnterOnMainPage,
            btnEnterLk,
            btnEnterOnRegistrationForm,
            btnEnterOnRecoverPassword})
    public void shouldUserAuthorizationSuccess(String condition) {
        open(BASE_URL);
        switch (condition) {
            case btnEnterOnMainPage: {
                page(MainPage.class)
                        .clickBtnLogin();
            }
            break;
            case btnEnterLk: {
                page(BasePage.class)
                        .clickBtnLabel(LK.getHeaderName());
            }
            break;
            case btnEnterOnRegistrationForm: {
                page(MainPage.class)
                        .clickBtnLogin()
                        .clickBtnRegister()
                        .clickBtnEnterFooter();
            }
            break;
            case btnEnterOnRecoverPassword: {
                page(MainPage.class)
                        .clickBtnLogin()
                        .clickBtnRecoverPassword()
                        .clickBtnEnterFooter();
            }
            break;

        }
        page(AuthorizationPage.class)
                .setEmail(body.getEmail())
                .setPassword(body.getPassword())
                .clickBtnSubmit();
    }
}
