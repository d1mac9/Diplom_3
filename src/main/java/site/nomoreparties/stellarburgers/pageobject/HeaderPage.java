package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Selenide.page;

public class HeaderPage {
    @FindBy(how = How.XPATH, using = "//p[text()='Личный Кабинет']")
    private SelenideElement btnLk;

    @Step("Нажать кнопку Личный кабинет")
    public void clickBtnLk() {
        btnLk.click();
    }
}
