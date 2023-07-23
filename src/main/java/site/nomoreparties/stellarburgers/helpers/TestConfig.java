package site.nomoreparties.stellarburgers.helpers;

public class TestConfig {
    public static String BROWSER = "chrome";
    public static String HEADLESS = "1";
    public static void initConfig() {
        BROWSER = System.getProperty("browser") == null ? "chrome" : System.getProperty("browser");
        HEADLESS = System.getProperty("headless") == null ? "0" : System.getProperty("headless");
    }

    public static boolean isHeadless(){
        return HEADLESS.contains("1");
    }
}
