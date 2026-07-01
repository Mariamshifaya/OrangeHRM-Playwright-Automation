package com.orangehrm;

import com.microsoft.playwright.*;

public class PlaywrightTest {

    public static void main(String[] args) {

        try (Playwright playwright = Playwright.create()) {

            Browser browser = playwright.chromium().launch(
                    new BrowserType.LaunchOptions()
                            .setHeadless(false)
            );

            Page page = browser.newPage();

            page.navigate("https://opensource-demo.orangehrmlive.com/");

            System.out.println(page.title());

            browser.close();
        }
    }
}