package site.nomoreparties.stellarburgers.page_object;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import static com.codeborne.selenide.Condition.*;

public class ConstructorPage {
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab__')]/child::*")
    private ElementsCollection tabsConstructor;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'tab_tab_type_current__')]/child::*")
    private SelenideElement activeTab;
    @FindBy(how = How.XPATH, using = "//*[contains(@class, 'BurgerIngredients_ingredients__menuContainer__')]/*[contains(@class,'text_type_main-medium')]")
    private ElementsCollection labelMenu;

    @Step("Нажать на выбранную табу в конструкторе")
    public ConstructorPage clickTabConstructor(String tabName) {
        tabsConstructor.findBy(text(tabName)).click();
        return this;
    }

    @Step("Проверка, что таба активна")
    public ConstructorPage checkActiveTabConstructor(String tabName) {
        activeTab.shouldHave(exactText(tabName));
        return this;
    }

    @Step("Проверка, что отображается корректный заголовок меню")
    public ConstructorPage checkLabelMenu(String menuName) {
        labelMenu.findBy(text(menuName)).shouldBe(visible);
        return this;
    }
}
