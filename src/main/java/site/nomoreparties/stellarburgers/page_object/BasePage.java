package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class , 'AppHeader_header__linkText__')]")
    private ElementsCollection btnLabel;
    @FindBy(how = How.XPATH, using = "//*[@xmlns = 'http://www.w3.org/2000/svg']")
    private SelenideElement mainLabel;
    @FindBy(how = How.XPATH, using = "//*[contains(@class , 'AppHeader_header__link_active__')]")
    private SelenideElement activeLabel;

    @Step("Нажать кнопку Личный кабинет")
    public BasePage clickBtnLabel(String labelName) {
        btnLabel.findBy(exactText(labelName)).click();
        return this;
    }

    @Step("Добавление токена в local storage браузера")
    public BasePage addTokenToLocalStorage(String accessToken) {
        executeJavaScript(String.format("localStorage.setItem('accessToken', '%s');", accessToken));
        return this;
    }

    @Step("Нажать на центральный логотип")
    public BasePage clickOnMainLabel() {
        mainLabel.click();
        return this;
    }

    @Step("Проверка, что заголовок активен")
    public void checkLabelIsActive(String labelName) {
        activeLabel.shouldHave(exactText(labelName));
    }

}
