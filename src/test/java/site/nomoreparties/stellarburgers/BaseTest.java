package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static site.nomoreparties.stellarburgers.api.ApiConfig.initApiConfig;
import static site.nomoreparties.stellarburgers.helpers.Driver.initDriver;

@ExtendWith(TextReportExtension.class)
public class BaseTest {
    @BeforeAll
    public static void setUp() {
        initApiConfig();
        initDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterAll
    static void afterAll() {
        closeWebDriver();
    }
}
