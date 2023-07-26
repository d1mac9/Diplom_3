package site.nomoreparties.stellarburgers.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;

public class LkPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'Account_listItem__')]/child::*")
    private ElementsCollection tabsLk;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'Account_link_active_')]")
    private SelenideElement activeTab;

    @Step("Проверка, что таба активна и отображается")
    public void checkLkTabIsActive(String tabName) {
        activeTab.shouldHave(exactText(tabName));
    }

    @Step("Нажать на выбранный пункт бокового меню в личном кабинете")
    public void clickLkTab(String tabName) {
        tabsLk.findBy(text(tabName)).click();
    }
}
