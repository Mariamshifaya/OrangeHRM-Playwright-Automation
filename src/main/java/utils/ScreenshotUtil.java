package utils;

import com.microsoft.playwright.Page;

import java.io.File;
import java.nio.file.Paths;

public class ScreenshotUtil {

    public static String captureScreenshot(Page page, String testName) {

        String path = "Screenshots/" + testName + ".png";

        page.screenshot(new Page.ScreenshotOptions()
                .setPath(Paths.get(path))
                .setFullPage(true));

        return new File(path).getAbsolutePath();
    }

}