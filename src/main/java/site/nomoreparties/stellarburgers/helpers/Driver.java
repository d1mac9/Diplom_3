package site.nomoreparties.stellarburgers.helpers;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Driver {
    public static void initDriver() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.holdBrowserOpen = true;
        Configuration.screenshots = true;
        Configuration.headless = TestConfig.isHeadless();
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site/";

        switch (TestConfig.BROWSER) {
            case "yandex":
                initYandexDriver();
                break;
            case "chrome":
                Configuration.browser = Browsers.CHROME;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
        }

    }

    public static void initYandexDriver() {
        ChromeOptions options = new ChromeOptions();
        System.setProperty("webdriver.chrome.driver", "WebDriver/yandexdriver.exe");
        options.setBinary(System.getenv("programfiles(x86)") + "Yandex/YandexBrowser/Application/browser.exe");
    }

    @Step("Проверка отображения корректного url")
    public static boolean waitForUrlContains(String urlChunk) {
        WebDriverWait wait = new WebDriverWait(WebDriverRunner.getWebDriver(), Duration.ofSeconds(10));
        try {
            wait.until(ExpectedConditions.urlContains(urlChunk));
        } catch (TimeoutException e) {
            return false;
        }
        return true;
    }
}
