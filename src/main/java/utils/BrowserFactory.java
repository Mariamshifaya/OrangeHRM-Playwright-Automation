package utils;

import com.microsoft.playwright.*;

public class BrowserFactory {

    public static Browser launchBrowser(Playwright playwright) {

        String browser = ConfigReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));

        BrowserType.LaunchOptions options =
                new BrowserType.LaunchOptions()
                        .setHeadless(headless);

        switch (browser.toLowerCase()) {

            case "firefox":
                return playwright.firefox().launch(options);

            case "webkit":
                return playwright.webkit().launch(options);

            case "chromium":
            default:
                return playwright.chromium().launch(options);
        }
    }
}