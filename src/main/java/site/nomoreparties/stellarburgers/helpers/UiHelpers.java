package site.nomoreparties.stellarburgers.helpers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.url;

public class UiHelpers {
    @Step("Проверка URL страницы")
    public static void checkUrl(String url) {
        webdriver().shouldHave(url(url));
    }
}
