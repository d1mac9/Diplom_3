package site.nomoreparties.stellarburgers.helpers;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Step;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class Driver {

    public static void initDriver() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false;
        Configuration.screenshots = true;
        Configuration.headless = TestConfig.isHeadless();
        Configuration.baseUrl = "https://stellarburgers.nomoreparties.site/";

        TestConfig.initConfig();

        switch (TestConfig.browser) {
            case "yandex":
                initYandexDriver();
                break;
            case "firefox":
                Configuration.browser = Browsers.FIREFOX;
                break;
            default:
                Configuration.browser = Browsers.CHROME;
        }

    }

    public static void initYandexDriver() {
        //Путь до вебдрайвера яндекса
        System.setProperty("webdriver.chrome.driver", "C:/WebDriver/bin/yandexdriver.exe");
        Configuration.browser = Browsers.CHROME;
        ChromeOptions options = new ChromeOptions();
        //Путь до яндекс браузера
        options.setBinary("C:\\Users\\BSPB\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        options.addArguments("--remote-allow-origins=*");
        if (TestConfig.isHeadless()) {
            options.addArguments("--headless=new");
        }
        WebDriver webDriver = new ChromeDriver(options);
        setWebDriver(webDriver);
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
