package tests;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.DashboardPage;
import pages.LoginPage;

public class LogoutTest extends BaseClass {

    @Test
    public void verifyLogout() {

        LoginPage loginPage = new LoginPage(getPage());
        DashboardPage dashboardPage = new DashboardPage(getPage());

        // Login
        loginPage.login();
        getPage().waitForTimeout(3000);

        // Verify Dashboard
        Assert.assertTrue(
                dashboardPage.isDashboardDisplayed(),
                "Dashboard not displayed."
        );

        // Logout
        dashboardPage.logout();

        // Verify Login Page
        Assert.assertTrue(
                dashboardPage.isLoginPageDisplayed(),
                "Logout failed."
        );

        System.out.println("✅ Logout Test Passed");
    }
}