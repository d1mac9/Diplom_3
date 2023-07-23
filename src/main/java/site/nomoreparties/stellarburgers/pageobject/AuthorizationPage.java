package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.visible;

public class AuthorizationPage {
    @FindBy(how = How.XPATH, using = "//label[text()='Имя']/following-sibling::input")
    private SelenideElement fldUserName;
    @FindBy(how = How.XPATH, using = "//label[text()='Email']/following-sibling::input")
    private SelenideElement fldEmail;
    @FindBy(how = How.NAME, using = "Пароль")
    private SelenideElement fldPassword;
    @FindBy(how = How.XPATH, using = "//button[text()='Войти']")
    private SelenideElement btnSubmit;
    @FindBy(how= How.XPATH, using = "//*[text()='Зарегистрироваться']")
    private SelenideElement btnRegister;
    @FindBy(how = How.XPATH, using = "//p[contains(@class , 'input__error') and text() = 'Некорректный пароль']")
    private SelenideElement validErrorPassword;
    @FindBy(how = How.LINK_TEXT, using = "Войти")
    private SelenideElement btnEnterFooterRegistrationFrom;
    @FindBy(how = How.LINK_TEXT, using = "Восстановить пароль")
    private SelenideElement btnRecoverPassword;

    @Step("Заполнить Имя")
    public AuthorizationPage setUserName(String userName){
        fldUserName.setValue(userName);
        return this;
    }

    @Step("Заполнение поля email")
    public AuthorizationPage setEmail(String email){
        fldEmail.setValue(email);
        return this;
    }
    @Step("Заполнение поля password")
    public AuthorizationPage setPassword(String password){
        fldPassword.setValue(password);
        return this;
    }

    @Step("Нажать кнопку Войти")
    public void clickBtnSubmit(){
        btnSubmit.click();
    }

    @Step("Нажать кнопку зарегистрироваться")
    public AuthorizationPage clickBtnRegister(){
        btnRegister.click();
        return this;
    }
    @Step("Проверка видимости валидационной подсказки Некорректный запрос для поля password")
    public AuthorizationPage checkValidErrorIsVisible(){
        validErrorPassword.shouldBe(visible);
        return this;
    }
    @Step("Нажать кнопку Войти на форме регистрации")
    public AuthorizationPage clickBtnEnterFooter(){
        btnEnterFooterRegistrationFrom.click();
        return this;
    }
    @Step("Нажать кнопку Восстановить пароль")
    public AuthorizationPage clickBtnRecoverPassword(){
        btnRecoverPassword.click();
        return this;
    }
}
