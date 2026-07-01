package pages;

import com.microsoft.playwright.Page;

public class DashboardPage {

    private Page page;

    public DashboardPage(Page page) {
        this.page = page;
    }

    // Locators
    private String dashboardHeader = "//h6[text()='Dashboard']";
    private String profileMenu = ".oxd-userdropdown-tab";
    private String logoutLink = "text=Logout";
    private String loginHeader = "//h5[text()='Login']";

    // Verify Dashboard
    public boolean isDashboardDisplayed() {

        try {

            page.waitForURL("**/dashboard/**");

            return page.locator("//h6[text()='Dashboard']")
                    .isVisible();

        } catch (Exception e) {

            return false;
        }
    }

    // Click Profile
    public void clickProfileMenu() {

        page.click(profileMenu);

    }

    // Click Logout
    public void clickLogout() {

        page.click(logoutLink);

    }

    // Complete Logout
    public void logout() {

        clickProfileMenu();

        page.waitForTimeout(1000);

        clickLogout();

    }

    // Verify Login Page
    public boolean isLoginPageDisplayed() {

        page.waitForSelector(loginHeader);

        return page.locator(loginHeader).isVisible();

    }

}