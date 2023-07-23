package site.nomoreparties.stellarburgers;

import com.codeborne.selenide.junit5.TextReportExtension;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;

import static site.nomoreparties.stellarburgers.helpers.Driver.initDriver;
@ExtendWith(TextReportExtension.class)
public class BaseTest  {
    @BeforeAll
    public static void setUp(){
//        initDriver();
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));

    }
}
