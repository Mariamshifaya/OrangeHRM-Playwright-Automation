package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

public class BaseClass {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;

    // Thread-safe Page object
    private static ThreadLocal<Page> page = new ThreadLocal<>();

    // Getter for Page
    public static Page getPage() {
        return page.get();
    }

    @BeforeMethod
    @Parameters("browser")
    public void launchBrowser(@Optional("chromium") String browserName) {

        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(
                ConfigReader.getProperty("headless"));

        switch (browserName.toLowerCase()) {

            case "firefox":
                browser = playwright.firefox().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;

            case "webkit":
                browser = playwright.webkit().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;

            case "chromium":
            case "chrome":
            default:
                browser = playwright.chromium().launch(
                        new BrowserType.LaunchOptions()
                                .setHeadless(headless));
                break;
        }

        context = browser.newContext();

        page.set(context.newPage());

        getPage().navigate(ConfigReader.getProperty("url"));

        System.out.println("================================");
        System.out.println("Browser Started : " + browserName);
        System.out.println("Thread ID : " + Thread.currentThread().getId());
        System.out.println("================================");
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        if (context != null)
            context.close();

        if (browser != null)
            browser.close();

        if (playwright != null)
            playwright.close();

        page.remove();

        System.out.println("Browser Closed");
    }
}