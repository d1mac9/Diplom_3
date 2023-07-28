package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class MainPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//button[text()='Войти в аккаунт']")
    private SelenideElement btnLogin;

    @Step("Нажать кнопку Войти в аккаунт")
    public AuthorizationPage clickBtnLogin() {
        btnLogin.click();
        return page(AuthorizationPage.class);
    }
}
